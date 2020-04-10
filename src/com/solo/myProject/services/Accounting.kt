package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.models.Session
import com.solo.myProject.models.Resource
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.sql.Connection
import java.time.LocalDate

class Accounting(
        private val de: String,
        private val ds: String,
        private val vol: String,
        private val login: String,
        private val argRes: String,
        private val role: String,
        private val user: User,
        private var sessions: MutableList<Session>,
        private val resources: List<Resource>,
        private val conn: Connection
) {

    private val res = getRes()
    private val log: Logger = LogManager.getLogger()

    fun start(): Int {

        if(!needAcc())
            return ExitCodes.Success.code
        log.info("Start Accounting")
        if (!checkDate()) {
            log.info(de + " or " + ds + " is incorrect date")
            return ExitCodes.IncorrectActivity.code
        }
        if (!checkVol()) {
            log.info(vol + " is incorrect volume")
            return ExitCodes.IncorrectActivity.code
        }
        log.info("User with login " + login + " used " + argRes + " with role " + role)

        val session = if (res != null) Session(user, res, LocalDate.parse(ds), LocalDate.parse(ds), vol.toInt()) else null
        if (session != null)
            sessions.add(session)

        log.info("CreateStatement for insert Session")
        val resultSet = "INSERT INTO session (user, res, ds, de, vol) Values (?, ?, ?, ?, ?)"
        val statement = conn.prepareStatement(resultSet)
        statement.setString(1, login)
        statement.setString(2, argRes)
        statement.setString(3, ds)
        statement.setString(4, de)
        statement.setString(5, vol)
        statement.execute()
        statement.close()
        log.info("CreateStatement close")

        return ExitCodes.Success.code
    }

    private fun checkDate(): Boolean {
        return try {
            val timeStart = LocalDate.parse(ds)
            val timeEnd = LocalDate.parse(de)
            timeStart < timeEnd
        } catch (e: Exception) {
            false
        }
    }

    fun checkVol(): Boolean {
        return try {
            vol.toInt() > 0
        } catch (e: Exception) {
            false
        }
    }

    private fun getRes(): Resource? {
        return resources[resources.indexOf(resources.find { it.res == argRes })]
    }

    fun needAcc(): Boolean = ds != ""
}
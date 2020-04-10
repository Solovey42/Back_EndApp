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

class Accounting(private val arg: ArgHandler, private val user: User, private var sessions: MutableList<Session>, private val resources: List<Resource>, private val conn: Connection) {

    private val res = getRes()
    private val log: Logger = LogManager.getLogger()

    fun start(): Int {
        if (!arg.needAcc())
            return ExitCodes.Success.code
        log.info("Start Accounting")
        if (!arg.checkDate()) {
            log.info(arg.de + " or " + arg.ds + " is incorrect date")
            return ExitCodes.IncorrectActivity.code
        }
        if (!arg.checkVol()) {
            log.info(arg.vol + " is incorrect volume")
            return ExitCodes.IncorrectActivity.code
        }
        log.info("User with login " + arg.login + " used " + arg.res + " with role " + arg.role)
        addSession()
        return ExitCodes.Success.code
    }

    private fun addSession() {
        val session = if (res != null) Session(user, res, LocalDate.parse(arg.ds), LocalDate.parse(arg.ds), arg.vol.toInt()) else null
        if (session != null)
            sessions.add(session)

        log.info("CreateStatement for insert Session")
        val resultSet = "INSERT INTO session (user, res, ds, de, vol) Values (?, ?, ?, ?, ?)"
        val statement = conn.prepareStatement(resultSet)
        statement.setString(1, arg.login)
        statement.setString(2, arg.res)
        statement.setString(3, arg.ds)
        statement.setString(4, arg.de)
        statement.setString(5, arg.vol)
        statement.execute()
        statement.close()
        log.info("CreateStatement close")
    }

    private fun getRes(): Resource? {
        return resources[resources.indexOf(resources.find { it.res == arg.res })]
    }


}
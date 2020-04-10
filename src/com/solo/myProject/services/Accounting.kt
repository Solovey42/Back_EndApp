package com.solo.myProject.services

import com.solo.myProject.DataAccessLayer
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.models.Session
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.time.LocalDate

class Accounting(private val needAcc: Boolean,
                 private val checkDate: Boolean,
                 private val checkVol: Boolean,
                 private val ds: String,
                 private val de: String,
                 private val vol: String,
                 private val login: String,
                 private val role: String,
                 private val res: String,
                 private val user: User,
                 private val dal: DataAccessLayer) {

    private val log: Logger = LogManager.getLogger()

    fun start(): Int {
        if (!needAcc)
            return ExitCodes.Success.code
        log.info("Start Accounting")
        if (!checkDate) {
            log.info("$de or $ds is incorrect date")
            return ExitCodes.IncorrectActivity.code
        }
        if (!checkVol) {
            log.info("$vol is incorrect volume")
            return ExitCodes.IncorrectActivity.code
        }
        log.info("User with login $login used $res with role $role")
        addSession()
        return ExitCodes.Success.code
    }

    private fun addSession() {
        val session = Session(user, res, LocalDate.parse(ds), LocalDate.parse(ds), vol.toInt())
        dal.addSession(session)
    }

//    private fun getRes(): Resource? {
//        return resources[resources.indexOf(resources.find { it.res == .res })]
//    }


}
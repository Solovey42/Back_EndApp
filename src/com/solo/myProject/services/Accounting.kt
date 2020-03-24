package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.models.Session
import com.solo.myProject.models.Resource
import com.solo.myProject.models.User
import java.time.LocalDate

class Accounting(private val arg: ArgHandler, private val user: User, var sessions: MutableList<Session>, private val resources: List<Resource>) {

    private val res = getRes()

    fun start(): Int {
        if (!arg.needAcc())
            return ExitCodes.Success.code
        if (!arg.checkDate())
            return ExitCodes.IncorrectActivity.code
        if (!arg.checkVol())
            return ExitCodes.IncorrectActivity.code
        addSession()
        return ExitCodes.Success.code
    }

    private fun addSession() {
        val session = if (res != null) Session(user, res, LocalDate.parse(arg.ds), LocalDate.parse(arg.ds), arg.vol.toInt()) else null
        if (session != null)
            sessions.add(session)
    }

    private fun getRes(): Resource? {
        return resources[resources.indexOf(resources.find { it.res == arg.res })]
    }
}
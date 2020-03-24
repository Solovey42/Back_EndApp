package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.models.Session
import com.solo.myProject.models.Resource
import com.solo.myProject.models.User
import java.time.LocalDate

class Accounting(argHandler: ArgHandler, User: User, Sessions: List<Session>, Resources: List<Resource>) {

    private val arg: ArgHandler = argHandler
    private val user = User
    private val resources = Resources
    private var sessions = Sessions.toMutableList()
    private val res = getRes()



    fun start():Int {
        return if (arg.NeedAcc())
            if (arg.CheckDate())
                if (arg.CheckVol()) {
                    addSession()
                    ExitCodes.Success.code
                } else ExitCodes.IncorrectActivity.code
            else ExitCodes.IncorrectActivity.code
        else 0
    }

    private fun addSession() {
        val session = if (res != null) Session(user, res, LocalDate.parse(arg.ds), LocalDate.parse(arg.ds), arg.vol.toInt()) else null
        sessions.add(session!!)

    }

    private fun getRes(): Resource? {
        return resources[resources.indexOf(resources.find { it.res == arg.res })]
    }
}
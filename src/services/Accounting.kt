package services

import ArgHandler
import enums.ExitCodes
import models.Session
import models.Resource
import models.User
import java.time.LocalDate
import kotlin.system.exitProcess

class Accounting(argHandler: ArgHandler, User: User, Sessions: List<Session>, Resources: List<Resource>) {

    private val arg: ArgHandler = argHandler
    private val user = User
    private val resources = Resources
    private var sessions = Sessions.toMutableList()
    private val res = getRes()

    init {
        start()
    }

    private fun start() {
        if (arg.NeedAcc())
            if (arg.CheckDate())
                if (arg.CheckVol()) {
                    addSession()
                    exitProcess(ExitCodes.Success.code)
                } else exitProcess(ExitCodes.IncorrectActivity.code)
            else exitProcess(ExitCodes.IncorrectActivity.code)
    }

    private fun addSession() {
        val session = if (res != null) Session(user, res, LocalDate.parse(arg.ds), LocalDate.parse(arg.ds), arg.vol.toInt()) else null
        sessions.add(session!!)

    }

    private fun getRes(): Resource? {
        return resources[resources.indexOf(resources.find { it.res == arg.res })]
    }
}
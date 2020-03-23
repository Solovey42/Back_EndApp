package services

import ArgHandler
import enums.ExitCodes
import Resources
import models.Session
import Sessions
import Users
import models.Resource
import models.User
import java.time.LocalDate
import kotlin.system.exitProcess

class Accounting(argHandler: ArgHandler, User: User) {

    private val arg: ArgHandler = argHandler
    private val user = User
    private val res = getRes()

    init {
        start()
    }

    private fun start() {
        if (arg.NeedAcc())
            if (arg.CheckDate())
                if (arg.CheckVol()) {
                    AddSession()
                    exitProcess(ExitCodes.Success.code)
                } else exitProcess(ExitCodes.IncorrectActivity.code)
            else exitProcess(ExitCodes.IncorrectActivity.code)
    }

    private fun AddSession() {
        val session = if (res != null) Session(user, res, LocalDate.parse(arg.ds), LocalDate.parse(arg.ds), arg.vol.toInt()) else null
        Sessions.add(session!!)


    }

    private fun getRes(): Resource? {
        return Resources[Resources.indexOf(Resources.find { it.res == arg.res })]
    }
}
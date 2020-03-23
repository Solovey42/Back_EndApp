package services

import ArgHandler
import enums.ExitCodes
import Resources
import enums.Roles
import models.Resource
import models.User
import kotlin.system.exitProcess

class Authorization(argHandler: ArgHandler, User: User) {

    private val user = User
    private val arg = argHandler


    init {
        if (argHandler.NeedAuthorization())
            start()
    }

    private fun start() {
        if (arg.NeedAuthorization())
            checkResRole()

    }

    private fun checkResRole() {
        if (arg.role != Roles.EXECUTE.name && arg.role != Roles.READ.name && arg.role != Roles.WRITE.name)
            exitProcess(ExitCodes.UnknownRole.code)
        if (!arg.CheckResName())
            exitProcess((ExitCodes.UnknownRole.code))

        val nodes = arg.res.split(".")
        for (index in nodes.indices) {
            val currentNode = nodes.subList(0, index + 1).joinToString(".")
            if (Resources.any { it.res == currentNode && it.role.name == arg.role && it.user == user })
                if (!arg.NeedAcc())
                    exitProcess(ExitCodes.Success.code)
                else
                    Accounting(arg, user)

        }

        exitProcess(ExitCodes.NoAccess.code)
    }


}
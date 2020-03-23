package services

import ArgHandler
import enums.ExitCodes
import enums.Roles
import models.Resource
import models.Session
import models.User
import kotlin.system.exitProcess

class Authorization(argHandler: ArgHandler, User: User, Resources: List<Resource>, Sessions: List<Session>) {

    private val user = User
    private val arg = argHandler
    private val resources = Resources
    private val sessions = Sessions


    init {
        if (argHandler.NeedAuthorization())
            start()
    }

    private fun start() {
        if (arg.NeedAuthorization())
            checkResRole()

    }

    private fun checkResRole() {
        if (!Roles.check().contains(arg.role))
            exitProcess(ExitCodes.UnknownRole.code)
        if (!arg.CheckResName())
            exitProcess((ExitCodes.UnknownRole.code))

        val nodes = arg.res.split(".")
        for (index in nodes.indices) {
            val currentNode = nodes.subList(0, index + 1).joinToString(".")
            if (resources.any { it.res == currentNode && it.role.name == arg.role && it.user == user })
                if (!arg.NeedAcc())
                    exitProcess(ExitCodes.Success.code)
                else
                    Accounting(arg, user, sessions, resources)

        }

        exitProcess(ExitCodes.NoAccess.code)
    }


}
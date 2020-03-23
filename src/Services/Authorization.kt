package Services

import ArgHandler
import Enums.ExitCodes
import Resources
import Enums.Roles
import kotlin.system.exitProcess

class Authorization(argHandler: ArgHandler) {
    init {
        if (argHandler.NeedAuthorization())
            start(argHandler)
    }

    private fun start(argHandler: ArgHandler) {
        if (argHandler.NeedAuthorization())
            checkResRole(argHandler)

    }

    private fun checkResRole(argHandler: ArgHandler): Boolean {
        if (argHandler.role != Roles.EXECUTE.name && argHandler.role != Roles.READ.name && argHandler.role != Roles.WRITE.name)
            exitProcess(ExitCodes.UnknownRole.code)
        if (!argHandler.CheckResName())
            exitProcess((ExitCodes.UnknownRole.code))

        val nodes = argHandler.res.split(".")
        for (index in nodes.indices) {
            val currentNode = nodes.subList(0, index + 1).joinToString(".")
            if (Resources.any { it.res == currentNode && it.role.name == argHandler.role })
                if (!argHandler.NeedAcc())
                    exitProcess(ExitCodes.Success.code)
                else
                    Accounting(argHandler)

        }

        exitProcess(ExitCodes.NoAccess.code)
    }
}
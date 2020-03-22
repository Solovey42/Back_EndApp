import kotlin.system.exitProcess

class Authorization(argHandler: ArgHandler) {
    init {
        if (argHandler.NeedAuthorization())
            start(argHandler)
    }

    private fun start(argHandler: ArgHandler) {
        if (argHandler.NeedAuthorization())
            CheckResRole(argHandler)

    }

    fun CheckResRole(argHandler: ArgHandler): Boolean {
        if (argHandler.role != Roles.EXECUTE.name && argHandler.role != Roles.READ.name && argHandler.role != Roles.WRITE.name)
            exitProcess(5)
        val resources = Resources.find { it.user.login == argHandler.login }

        val nodes = argHandler.res.split(".")
        for (index in nodes.indices) {
            val currentNode = nodes.subList(0, index + 1).joinToString(".")
            if (Resources.any { it.res == currentNode && it.role.name == argHandler.role })
                if (!argHandler.NeedAcc())
                    exitProcess(0)
                else
                    Accounting(argHandler)

        }

        exitProcess(6)
    }
}
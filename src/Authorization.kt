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
}
import kotlin.system.exitProcess

class Authentication(argHandler: ArgHandler) {

    init {
        if (argHandler.NeedAuth())
            start(argHandler)
    }

    private fun start(argHandler: ArgHandler) {
        if (argHandler.NeedAuth())
            if (ValidateLogin(argHandler))
                if (CheckLogin(argHandler))
                    if (CheckLoinPass(argHandler))
                    else
                        exitProcess(ExitCodes.InvalidPassword.code)
                else
                    exitProcess(ExitCodes.UnknownLogin.code)
            else
                exitProcess(ExitCodes.InvalidLoginFormat.code)
        else
            exitProcess(ExitCodes.Success.code)
    }


}
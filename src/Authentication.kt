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

    fun ValidateLogin(argHandler: ArgHandler): Boolean {

        return argHandler.login.matches(Regex("[a-z]{1,10}"))

    }

    fun CheckLogin(argHandler: ArgHandler): Boolean {
        return Users.contains(Users.find { it.login == argHandler.login })
    }

    fun CheckLoinPass(argHandler: ArgHandler): Boolean {

        return Users[Users.indexOf(Users.find { it.login == argHandler.login })].pass == argHandler.password
    }


}
package services

import ArgHandler
import enums.ExitCodes
import Users
import kotlin.system.exitProcess

class Authentication(argHandler: ArgHandler) {

    init {
        if (argHandler.NeedAuth())
            start(argHandler)
    }

    private fun start(argHandler: ArgHandler) {
        if (argHandler.NeedAuth())
            if (argHandler.ValidateLogin())
                if (checkLogin(argHandler))
                    if (checkLoinPass(argHandler))
                        Authorization(argHandler)
                    else
                        exitProcess(ExitCodes.InvalidPassword.code)
                else
                    exitProcess(ExitCodes.UnknownLogin.code)
            else
                exitProcess(ExitCodes.InvalidLoginFormat.code)
        else
            exitProcess(ExitCodes.Success.code)
    }


    private fun checkLogin(argHandler: ArgHandler): Boolean {
        return Users.contains(Users.find { it.login == argHandler.login })
    }

    private fun checkLoinPass(argHandler: ArgHandler): Boolean {

        return Users[Users.indexOf(Users.find { it.login == argHandler.login })].pass == argHandler.password
    }


}
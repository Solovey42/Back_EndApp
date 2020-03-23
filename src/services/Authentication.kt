package services

import ArgHandler
import enums.ExitCodes
import Users
import models.User
import java.security.MessageDigest
import kotlin.system.exitProcess

class Authentication(argHandler: ArgHandler) {

    private val user = getUser(argHandler)

    init {
        if (argHandler.NeedAuth())
            start(argHandler)

    }


    private fun start(argHandler: ArgHandler) {

        if (argHandler.NeedAuth())
            if (argHandler.ValidateLogin())
                if (user != null)
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


    private fun getUser(argHandler: ArgHandler): User? {
        return Users.find { it.login == argHandler.login }
    }


    private fun checkLoinPass(argHandler: ArgHandler): Boolean {

        return user!!.hash == generateHash(argHandler.password, user.salt)
    }


    fun generateHash(plaintext: String, salt: String) =
            MessageDigest.getInstance("SHA-256")
                    .digest((plaintext + salt).toByteArray())
                    .fold("", { str, it -> str + "%02x".format(it) })


}
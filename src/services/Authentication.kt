package services

import ArgHandler
import enums.ExitCodes
import Users
import models.User
import java.security.MessageDigest
import kotlin.system.exitProcess

class Authentication(argHandler: ArgHandler) {

    private val arg: ArgHandler = argHandler
    private val user = getUser()

    init {
        if (argHandler.NeedAuth())
            start()
        else
            exitProcess(ExitCodes.Success.code)

    }


    private fun start() {

        if (arg.ValidateLogin())
            if (user != null)
                if (checkLoinPass())
                    Authorization(arg, user)
                else
                    exitProcess(ExitCodes.InvalidPassword.code)
            else
                exitProcess(ExitCodes.UnknownLogin.code)
        else
            exitProcess(ExitCodes.InvalidLoginFormat.code)

    }


    private fun getUser(): User? {
        return Users.find { it.login == arg.login }
    }


    private fun checkLoinPass(): Boolean {

        return user!!.hash == generateHash(arg.password, user.salt)
    }

    private fun generateHash(plaintext: String, salt: String) =
            MessageDigest.getInstance("SHA-256")
                    .digest((plaintext + salt).toByteArray())
                    .fold("", { str, it -> str + "%02x".format(it) })


}
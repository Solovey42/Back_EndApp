package services

import ArgHandler
import enums.ExitCodes
import models.Resource
import models.Session
import models.User
import java.security.MessageDigest
import kotlin.system.exitProcess

class Authentication(argHandler: ArgHandler,Users:List<User>,Resources:List<Resource>,Sessions:List<Session>) {

    private val arg: ArgHandler = argHandler
    private val users:List<User> = Users
    private  val resources = Resources
    private  val sessions = Sessions
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
                    Authorization(arg, user,resources,sessions)
                else
                    exitProcess(ExitCodes.InvalidPassword.code)
            else
                exitProcess(ExitCodes.UnknownLogin.code)
        else
            exitProcess(ExitCodes.InvalidLoginFormat.code)

    }


    private fun getUser(): User? {
        return users.find { it.login == arg.login }
    }

    private fun checkLoinPass(): Boolean {

        return user!!.hash == generateHash(arg.password, user.salt)
    }

    private fun generateHash(plaintext: String, salt: String) =
            MessageDigest.getInstance("SHA-256")
                    .digest((plaintext + salt).toByteArray())
                    .fold("", { str, it -> str + "%02x".format(it) })


}
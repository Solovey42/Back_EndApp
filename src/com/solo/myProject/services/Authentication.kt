package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.models.Resource
import com.solo.myProject.models.Session
import com.solo.myProject.models.User
import java.security.MessageDigest

class Authentication(private val arg: ArgHandler, private val users: List<User>, private val resources: List<Resource>, private val sessions: MutableList<Session>) {

    private val user = getUser()

    fun start(): Int {
        if (arg.checkArg())
            return ExitCodes.HelpCode.code
        if (arg.checkHelp())
            return ExitCodes.HelpCode.code
        if (!arg.needAuth())
            return ExitCodes.Success.code
        if (!arg.validateLogin())
            return ExitCodes.InvalidLoginFormat.code
        if (user == null)
            return ExitCodes.UnknownLogin.code
        if (!checkLoinPass())
            return ExitCodes.InvalidPassword.code
        return Authorization(arg, user, resources, sessions).start()
    }

    private fun getUser(): User? = users.find { it.login == arg.login }

    private fun checkLoinPass(): Boolean = user!!.hash == generateHash(arg.password, user.salt)

    private fun generateHash(plaintext: String, salt: String) =
            MessageDigest.getInstance("SHA-256")
                    .digest((plaintext + salt).toByteArray())
                    .fold("", { str, it -> str + "%02x".format(it) })
}
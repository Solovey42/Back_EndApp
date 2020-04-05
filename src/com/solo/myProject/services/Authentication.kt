package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.security.MessageDigest


class Authentication(private val arg: ArgHandler, private val users: List<User>) {

    private val user = getUser()

    private val log: Logger = LogManager.getLogger()
    fun start(): Int? {
        if (arg.checkArg()) {
            return ExitCodes.HelpCode.code
        }
        if (arg.checkHelp()) {
            return ExitCodes.HelpCode.code
        }
        if (!arg.needAuth())
            return ExitCodes.Success.code
        log.info("Start Authentication")
        if (!arg.validateLogin()) {
            log.info("Login " + arg.login + " invalid")
            return ExitCodes.InvalidLoginFormat.code
        }
        if (user == null) {
            log.info("User with login " + arg.login + " does not exist ")
            return ExitCodes.UnknownLogin.code
        }
        if (!checkLoinPass()) {
            log.info("Invalid password " + arg.password + " for user " + arg.login)
            return ExitCodes.InvalidPassword.code
        }
        log.info("Authentication user " + arg.login + " was successful")
        return null
    }

    fun getUser(): User? = users.find { it.login == arg.login }

    private fun checkLoinPass(): Boolean = user!!.hash == generateHash(arg.password, user.salt)

    private fun generateHash(plaintext: String, salt: String) =
            MessageDigest.getInstance("SHA-256")
                    .digest((plaintext + salt).toByteArray())
                    .fold("", { str, it -> str + "%02x".format(it) })
}
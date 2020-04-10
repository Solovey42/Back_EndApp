package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.DataAccessLayer
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.security.MessageDigest


class Authentication(private val login: String,
                     private val password: String,
                     private val dal: DataAccessLayer
) {

    private val log: Logger = LogManager.getLogger()
    fun start(): Int? {

        if (!validateLogin()) {
            log.info("Login $login invalid")
            return ExitCodes.InvalidLoginFormat.code
        }
        if (!dal.userExists(login)) {
            log.info("User with login $login does not exist ")
            return ExitCodes.UnknownLogin.code
        }
        if (!checkLoinPass()) {
            log.info("Invalid password $password for user $login")
            return ExitCodes.InvalidPassword.code
        }
        log.info("Authentication user $login was successful")
        return null
    }

    fun validateLogin(): Boolean = login.matches(Regex("[a-z]{1,10}"))

    private fun checkLoinPass(): Boolean = dal.getUser(login).hash == generateHash(password, dal.getUser(login).salt)

    private fun generateHash(plaintext: String, salt: String) =
            MessageDigest.getInstance("SHA-256")
                    .digest((plaintext + salt).toByteArray())
                    .fold("", { str, it -> str + "%02x".format(it) })
}
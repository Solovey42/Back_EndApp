package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.enums.Roles
import com.solo.myProject.models.Resource
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class Authorization(private val ds: String,
                    private val role: String,
                    private val res: String,
                    private val user: User,
                    private val resources: List<Resource>) {

    fun start(): Int? {
        if (!needAuthorization())
            return ExitCodes.Success.code
        log.info("Start Authorization")
        return checkResRole()
    }

    fun needAcc(): Boolean = ds != ""//Вот этот метод реализовать через поле этого класса

    private val log: Logger = LogManager.getLogger()

    private fun checkResRole(): Int? {
        if (Roles.check(role) == null) {
            log.info(role + " is unknown role")
            return ExitCodes.UnknownRole.code
        }
        if (!checkResName()) {
            log.info(res + " is unknown resource")
            return ExitCodes.UnknownRole.code
        }
        val nodes = res.split(".")
        for (index in nodes.indices) {
            val currentNode = nodes.subList(0, index + 1).joinToString(".")
            if (resources.any { it.res == currentNode && it.role == role && it.user == user })
                return if (!needAcc()) {
                    log.info("User with login " + user.login + " got access to resource " + res + " with role " + role)
                    ExitCodes.Success.code
                } else {
                    log.info("User with login " + user.login + " got access to resource " + res + " with role " + role)
                    return null
                }
        }
        log.info("User with login " + user.login + " does not have access to resource " + res + " with role " + role)
        return ExitCodes.NoAccess.code
    }


    fun needAuthorization(): Boolean = res != ""

    fun checkResName(): Boolean = res.matches(Regex("[A-Z]+(|.[A-Z]+)+"))
}
package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.enums.Roles
import com.solo.myProject.models.Resource
import com.solo.myProject.models.Session
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class Authorization(private val arg: ArgHandler, private val user: User, private val resources: List<Resource>, var sessions: MutableList<Session>) {

    fun start(): Int? {
        if (!arg.needAuthorization())
            return ExitCodes.Success.code
        log.info("Start Authorization")
        return checkResRole()
    }

    private val log: Logger = LogManager.getLogger()
    private fun checkResRole(): Int? {
        if (!Roles.check(arg.role)) {
            log.info(arg.role + " is unknown role")
            return ExitCodes.UnknownRole.code
        }
        if (!arg.checkResName()) {
            log.info(arg.res + " is unknown resource")
            return ExitCodes.UnknownRole.code
        }
        val nodes = arg.res.split(".")
        for (index in nodes.indices) {
            val currentNode = nodes.subList(0, index + 1).joinToString(".")
            if (resources.any { it.res == currentNode && it.role.name == arg.role && it.user == user })
                return if (!arg.needAcc()) {
                    log.info("User with login " + arg.login + " got access to resource " + arg.res + " with role " + arg.role)
                    ExitCodes.Success.code
                } else {
                    log.info("User with login " + arg.login + " got access to resource " + arg.res + " with role " + arg.role)
                    return null
                }
        }
        log.info("User with login " + arg.login + " does not have access to resource " + arg.res + " with role " + arg.role)
        return ExitCodes.NoAccess.code
    }
}
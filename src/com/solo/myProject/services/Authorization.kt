package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.enums.Roles
import com.solo.myProject.models.Resource
import com.solo.myProject.models.Session
import com.solo.myProject.models.User

class Authorization(private val arg: ArgHandler, private val user: User, private val resources: List<Resource>, var sessions: MutableList<Session>) {

    fun start(): Int {
        if (!arg.needAuthorization())
            return ExitCodes.Success.code
        return checkResRole()
    }

    private fun checkResRole(): Int {
        if (!Roles.check(arg.role))
            return ExitCodes.UnknownRole.code
        if (!arg.checkResName())
            return ExitCodes.UnknownRole.code

        val nodes = arg.res.split(".")
        for (index in nodes.indices) {
            val currentNode = nodes.subList(0, index + 1).joinToString(".")
            if (resources.any { it.res == currentNode && it.role.name == arg.role && it.user == user })
                return if (!arg.needAcc())
                    ExitCodes.Success.code
                else
                    Accounting(arg, user, sessions, resources).start()
        }
        return ExitCodes.NoAccess.code
    }
}
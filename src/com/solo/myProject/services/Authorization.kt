package com.solo.myProject.services

import com.solo.myProject.ArgHandler
import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.enums.Roles
import com.solo.myProject.models.Resource
import com.solo.myProject.models.Session
import com.solo.myProject.models.User

class Authorization(argHandler: ArgHandler, User: User, Resources: List<Resource>, Sessions: List<Session>) {

    private val user = User
    private val arg = argHandler
    private val resources = Resources
    private val sessions = Sessions

    fun start():Int {
        return if (arg.NeedAuthorization())
            checkResRole()
        else
            0


    }

    private fun checkResRole():Int {
        if (!Roles.check().contains(arg.role))
            return ExitCodes.UnknownRole.code
        if (!arg.CheckResName())
            return ExitCodes.UnknownRole.code

        val nodes = arg.res.split(".")
        for (index in nodes.indices) {
            val currentNode = nodes.subList(0, index + 1).joinToString(".")
            if (resources.any { it.res == currentNode && it.role.name == arg.role && it.user == user })
                return if (!arg.NeedAcc())
                    ExitCodes.Success.code
                else
                    Accounting(arg, user, sessions, resources).start()

        }

        return ExitCodes.NoAccess.code
    }


}
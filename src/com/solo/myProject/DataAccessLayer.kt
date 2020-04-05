package com.solo.myProject

import com.solo.myProject.models.Resource
import com.solo.myProject.models.Session
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import java.sql.Connection


class DataAccessLayer(private val argHandler: ArgHandler, private val conn: Connection, private val users: MutableList<User>, private val resources: MutableList<Resource>) {

    private val logger = LogManager.getLogger()

    fun insertTables() {
        logArgs()
        getUsers()
        getRes()
    }

    private fun logArgs() {
        logger.error("Entered parameters:")
        if (argHandler.h != "") logger.info("-h")
        if (argHandler.login != "") logger.info("Login = ${argHandler.login}")
        if (argHandler.password != "") logger.info("Pass = ${argHandler.password}")
        if (argHandler.res != "") logger.info("Res = ${argHandler.res}")
        if (argHandler.role != "") logger.info("Role = ${argHandler.role}")
        if (argHandler.ds != "") logger.info("Ds = ${argHandler.ds}")
        if (argHandler.de != "") logger.info("De = ${argHandler.de}")
        if (argHandler.vol != "") logger.info("Vol = ${argHandler.vol}")
    }

    private fun getUsers() {
        logger.info("CreateStatement for get Users")
        val statement = conn.createStatement()
        val resultSet = statement.executeQuery("SELECT * FROM users")
        while (resultSet.next()) {
            users.add(User(resultSet.getString("login"), resultSet.getString("hash"), resultSet.getString("salt")))
        }
        statement.close()
        resultSet.close()
        logger.info("CreateStatement close")
    }

    private fun getRes() {
        logger.info("CreateStatement for get Resources")
        val statement = conn.createStatement()
        val resultSet = statement.executeQuery("SELECT * FROM resource")
        while (resultSet.next()) {
            resources.add(Resource(resultSet.getString("res"), resultSet.getString("role"), users.find { it.login == resultSet.getString("user") }!!))
        }
        statement.close()
        resultSet.close()
        logger.info("CreateStatement close")
    }
}
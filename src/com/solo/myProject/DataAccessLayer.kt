package com.solo.myProject

import com.solo.myProject.models.Resource
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import java.sql.Connection


class DataAccessLayer(private val conn: Connection, private val users: MutableList<User>, private val resources: MutableList<Resource>) {

    private val logger = LogManager.getLogger()

    fun insertTables() {
        getUsers()
        getRes()
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
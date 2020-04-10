package com.solo.myProject

import com.solo.myProject.models.Resource
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import java.sql.Connection


class DataAccessLayer(private val conn: Connection, private val users: MutableList<User>, private val resources: MutableList<Resource>) {

    private val logger = LogManager.getLogger()

    fun getUser(login: String): User {
        logger.info("Get prepared statement with users")
        val getUser = conn.prepareStatement("SELECT hash, salt FROM users WHERE login = ?")
        getUser.setString(1, login)
        logger.info("Get result set with user")
        val res = getUser.executeQuery()
        res.next()
        val salt = res.getString("salt")
        val hash = res.getString("hash")
        logger.info("Close result set with user")
        res.close()
        logger.info("Close prepared statement with users")
        getUser.close()
        return User(login, hash, salt)
    }
    fun userExists(login: String): Boolean {
        var isUserExists = false
        logger.info("Get prepared statement with user")
        val getUser = conn.prepareStatement("select* from users where login = ?")
        getUser.setString(1, login)
        logger.info("Get result set with user")
        var resultSet = getUser.executeQuery()
            if (resultSet.next())
                isUserExists =  true
        logger.info("Close result set with user")
        resultSet.close()
        logger.info("Close prepared statement with user")
        getUser.close()
        return isUserExists
    }

    fun accessToRes(res:String,login: String,role:String):Boolean
    {
        var isAccessExists = false
        logger.info("Get prepared statement with user")
        val getUser = conn.prepareStatement("select* from resource where user = ? and res = ? and role = ?")
        getUser.setString(1, login)
        getUser.setString(2, res)
        getUser.setString(3, role)
        logger.info("Get result set with user")
        var resultSet = getUser.executeQuery()
        if (resultSet.next())
            isAccessExists =  true
        logger.info("Close result set with user")
        resultSet.close()
        logger.info("Close prepared statement with user")
        getUser.close()
        return isAccessExists
    }

}
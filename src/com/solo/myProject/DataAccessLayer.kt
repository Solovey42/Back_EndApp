package com.solo.myProject

import com.solo.myProject.models.Resource
import com.solo.myProject.models.User
import org.apache.logging.log4j.LogManager
import java.sql.Connection


class DataAccessLayer(private val conn: Connection) {

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

    fun hasPermission(login: String, role: String, permissionRegex: String): Boolean {
        logger.info("Get prepared statement with permission")
        val getPermission = conn.prepareStatement(
                "SELECT count(*) FROM permissions WHERE login = ? and role = ? and res REGEXP ?")
        getPermission.setString(1, login)
        getPermission.setString(2, role)
        logger.info("Matching resources against '$permissionRegex'")
        getPermission.setString(3, permissionRegex)
        logger.info("Get result set with permission")
        val res = getPermission.executeQuery()
        res.next()
        val ans = res.getInt(1) > 0
        logger.info("Close result set with permission")
        res.close()
        logger.info("Close prepared statement with permission")
        getPermission.close()
        return ans
    }

    fun loginExists(login: String): Boolean {
        logger.info("Get prepared statement with user")
        val getUser = conn.prepareStatement("SELECT count(*) FROM users WHERE login = ?")
        getUser.setString(1, login)
        logger.info("Get result set with user")
        val res = getUser.executeQuery()
        res.next()
        val ans = res.getInt(1) > 0
        logger.info("Close result set with user")
        res.close()
        logger.info("Close prepared statement with user")
        getUser.close()
        return ans
    }
}
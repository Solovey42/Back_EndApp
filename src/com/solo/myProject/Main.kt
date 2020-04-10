package com.solo.myProject

import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.models.Resource
import com.solo.myProject.models.Session
import com.solo.myProject.models.User
import com.solo.myProject.services.Accounting
import com.solo.myProject.services.Authentication
import com.solo.myProject.services.Authorization
import org.apache.logging.log4j.LogManager
import org.flywaydb.core.Flyway
import java.io.File
import java.sql.DriverManager
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    val logger = LogManager.getLogger()

    logger.error("Start Program")
    if (!File("./db", "aaa.h2.db").exists()) {
        logger.info("Create database")
        val flyway = Flyway.configure().dataSource(System.getenv("URL") + ";MV_STORE=FALSE", System.getenv("LOGIN"), System.getenv("PASS")).locations("filesystem:db").load()
        flyway.migrate()
    }
    logger.info("Connect database")
    val conn = DriverManager.getConnection(System.getenv("URL") + ";MV_STORE=FALSE", System.getenv("LOGIN"), System.getenv("PASS"))


    val resources: MutableList<Resource> = mutableListOf()
    val sessions: MutableList<Session> = mutableListOf()

    val argHandler = ArgHandler(args)

    val DAL = DataAccessLayer(conn)




        val authentication = Authentication(
                argHandler.login,
                argHandler.password,
                DAL.loginExists(argHandler.login),
                DAL.getUser(argHandler.login))

    var returnCode = authentication.start()



    returnCode = if (returnCode == null && argHandler.needAuthorization()) {
        val authorization = Authorization(
                argHandler.ds,
                argHandler.role,
                argHandler.res,
                DAL.getUser(argHandler.login),
                DAL.hasPermission(argHandler.login, argHandler.role, argHandler.res))

        authorization.start()
    } else
        ExitCodes.Success.code

    if (returnCode == null && argHandler.needAcc()) {
        val accounting = Accounting(
                argHandler.de,
                argHandler.ds,
                argHandler.vol,
                argHandler.login,
                argHandler.res,
                argHandler.role,
                DAL.getUser(argHandler.login),
                sessions,
                resources,
                conn)
        returnCode = accounting.start()
    }
    else
        returnCode=ExitCodes.Success.code

    logger.info("Connect close")
    conn.close()
    exitProcess(returnCode)
}
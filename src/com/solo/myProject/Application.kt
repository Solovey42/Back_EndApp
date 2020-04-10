package com.solo.myProject

import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.services.Accounting
import com.solo.myProject.services.Authentication
import com.solo.myProject.services.Authorization
import org.flywaydb.core.Flyway
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import kotlin.system.exitProcess


fun connect(): Connection {
    if (!File("./db", "aaa.h2.db").exists()) {
        logger.info("Create database")
        val flyway = Flyway.configure().dataSource(System.getenv("URL") + ";MV_STORE=FALSE", System.getenv("LOGIN"), System.getenv("PASS")).locations("filesystem:db").load()
        flyway.migrate()
    }
    logger.info("Connect database")
    return DriverManager.getConnection(System.getenv("URL") + ";MV_STORE=FALSE", System.getenv("LOGIN"), System.getenv("PASS"))
}

fun businessLogic(args: Array<String>): Int {
    val connection = connect()
    logger.info("Database connected")

    val argHandler = ArgHandler(args)
    val dal = DataAccessLayer(connection)

    if (argHandler.checkArg()) {
        exitProcess(ExitCodes.HelpCode.code)
    }
    if (argHandler.checkHelp()) {
        exitProcess(ExitCodes.HelpCode.code)
    }
    if (!argHandler.needAuth())
        exitProcess(ExitCodes.Success.code)

    logger.info("Start Authentication")

    val authentication = Authentication(argHandler.login, argHandler.password, dal)
    var returnCode = authentication.start()

    if (returnCode == null) {

        val authorization = Authorization(argHandler.role,
                argHandler.res,
                dal,
                dal.getUser(argHandler.login),
                argHandler.ds)
        returnCode = authorization.start()
    }
    if (returnCode == null) {
        val accounting = Accounting(argHandler.needAcc(),
                argHandler.checkDate(),
                argHandler.checkVol(),
                argHandler.ds,
                argHandler.de,
                argHandler.vol,
                argHandler.login,
                argHandler.role,
                argHandler.res,
                dal.getUser(argHandler.login),
                dal)
        returnCode = accounting.start()
    }
    logger.info("Connection closed")
    connection.close()

    return returnCode
}
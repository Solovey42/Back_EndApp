package com.solo.myProject

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
        val flyway = Flyway.configure().dataSource(System.getenv("URL")+";MV_STORE=FALSE",  System.getenv("LOGIN"), System.getenv("PASS")).locations("filesystem:db").load()
        flyway.migrate()
    }
    logger.info("Connect database")
    val conn = DriverManager.getConnection(System.getenv("URL")+";MV_STORE=FALSE", System.getenv("LOGIN"), System.getenv("PASS"))

    val users: MutableList<User> = mutableListOf()
    val resources: MutableList<Resource> = mutableListOf()
    val sessions: MutableList<Session> = mutableListOf()

    val argHandler = ArgHandler(args)
    DataAccessLayer(conn, users, resources).insertTables()

    val authentication = Authentication(argHandler, users)
    var returnCode = authentication.start()
    if (returnCode == null) {
        val authorization = Authorization(argHandler, authentication.getUser()!!, resources)
        returnCode = authorization.start()
    }
    if (returnCode == null) {
        val accounting = Accounting(argHandler, authentication.getUser()!!, sessions, resources, conn)
        returnCode = accounting.start()
    }
    logger.info("Connect close")
    conn.close()
    exitProcess(returnCode)
}
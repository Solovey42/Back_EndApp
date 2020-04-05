package com.solo.myProject

import com.solo.myProject.enums.Roles
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
import java.sql.Statement
import kotlin.system.exitProcess


fun main(args: Array<String>) {

    val statement: Statement?
    logger.error("Start Program")
    if (!File("./db", "aaa.mv.db").exists()) {
        logger.info("Create database")
        val flyway = Flyway.configure().dataSource("jdbc:h2:file:./db/aaa", "Solo", "1234").locations("filesystem:db\\migration").load()
        flyway.migrate()
    }

    logger.info("Connect database")
    val conn = DriverManager.getConnection("jdbc:h2:file:./db/aaa", "Solo", "1234")
    statement = conn.createStatement()
    logger.info("CreateStatement")

    val argHandler = ArgHandler(args)
    logArgs(argHandler)
    val authentication = Authentication(argHandler, users, resources, sessions.toMutableList())
    var returnCode = authentication.start()
    if (returnCode == null) {
        val authorization = Authorization(argHandler, authentication.getUser()!!, resources, sessions)
        returnCode = authorization.start()
    }
    if (returnCode == null) {
        val accounting = Accounting(argHandler, authentication.getUser()!!, sessions, resources)
        returnCode = accounting.start()
    }
    exitProcess(returnCode)
}

private val logger = LogManager.getLogger()
private fun logArgs(argHandler: ArgHandler) {
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

val users: List<User> = listOf(
        User("vasya", "0a4b21dd234f89b25e9a39ed87273253dc1840ccf23ee2a90380c698280f5338", "dad1ef245"),
        User("admin", "d6fed044a4457acf2437d68189251a6d07918c9d7345421ec5c0a2f1043766a7", "dke94jgj4"),
        User("q", "580cbc37144acf152ca48ca59f6cc5b1fbc2a53b947cdf8c29939e979a030900", "djrl409fj"),
        User("abcd", "6f80cf73456754822a8c47261f488fc1c57267eab2981e360797b9876849b0f3", "dlk4u9f39")
)

val resources: List<Resource> = listOf(
        Resource("A", Roles.READ, users[0]),
        Resource("A.B.C", Roles.WRITE, users[0]),
        Resource("A.B", Roles.EXECUTE, users[1]),
        Resource("A", Roles.READ, users[1]),
        Resource("A.B", Roles.WRITE, users[1]),
        Resource("A.B.C", Roles.READ, users[1]),
        Resource("B", Roles.EXECUTE, users[2]),
        Resource("A.A.A", Roles.EXECUTE, users[0])
)

var sessions: MutableList<Session> = mutableListOf()
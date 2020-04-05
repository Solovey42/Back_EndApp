package com.solo.myProject

import com.solo.myProject.enums.Roles
import com.solo.myProject.models.Resource
import com.solo.myProject.models.Session
import com.solo.myProject.models.User
import com.solo.myProject.services.Accounting
import com.solo.myProject.services.Authentication
import com.solo.myProject.services.Authorization
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.flywaydb.core.Flyway
import java.sql.DriverManager
import kotlin.system.exitProcess


fun main(args: Array<String>) {

    val conn = DriverManager.getConnection("jdbc:h2:~/db", "Solo", "123")
    val flyway = Flyway.configure().dataSource("jdbc:h2:~/db", "Solo", "123").locations("filesystem:db\\migration").load()
    // Start the migration
    // Start the migration
    flyway.migrate()


    val log: Logger = LogManager.getLogger()
    log.error("StartProgramm")
    val argHandler = ArgHandler(args)
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
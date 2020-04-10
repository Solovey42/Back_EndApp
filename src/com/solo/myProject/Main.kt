package com.solo.myProject

import com.solo.myProject.enums.ExitCodes
import com.solo.myProject.models.Resource
import com.solo.myProject.models.Session
import com.solo.myProject.models.User
import com.solo.myProject.services.Accounting
import com.solo.myProject.services.Authentication
import com.solo.myProject.services.Authorization
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.flywaydb.core.Flyway
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import kotlin.system.exitProcess


val logger: Logger = LogManager.getLogger()

fun main(args: Array<String>) {
    logger.error("Start Program")
    exitProcess(businessLogic(args))
}
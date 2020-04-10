package com.solo.myProject

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.system.exitProcess


val logger: Logger = LogManager.getLogger()

fun main(args: Array<String>) {
    logger.error("Start Program")
    exitProcess(businessLogic(args))
}
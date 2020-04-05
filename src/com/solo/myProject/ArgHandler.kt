package com.solo.myProject

import java.time.LocalDate

class ArgHandler(args: Array<String>) {
    private val arr: Array<String> = args
    var login: String = ""
    var password: String = ""
    var role: String = ""
    var res: String = ""
    var ds: String = ""
    var de: String = ""
    var vol: String = ""
    var h: String = ""

    init {
        for (i in arr.indices) {
            when (args[i]) {
                "-login" -> login = args[i + 1]
                "-pass" -> password = args[i + 1]
                "-role" -> role = args[i + 1]
                "-res" -> res = args[i + 1]
                "-ds" -> ds = args[i + 1]
                "-de" -> de = args[i + 1]
                "-vol" -> vol = args[i + 1]
                "-h" -> h = args[i]
            }
        }
    }

    fun checkArg(): Boolean {
        if (arr.isEmpty()) {
            printHelp()
            return true
        }
        return false
    }

    private fun printHelp() = print(help)

    private val help: String =
            "-h - help\n" +
            "-login <str> -pass <str> - Authentication s\n" +
            "-login <str> -pass <str> -res <str> -role <str> - Authorization\n" +
            "-ds <YYYY-MM-DD> -de <YYYY-MM-DD> -vol <int> - Accounting\n"

    fun checkHelp(): Boolean {
        if (h == "-h") {
            printHelp()
            return true
        }
        return false
    }

    fun needAuth(): Boolean = login != "" && password != ""

    fun validateLogin(): Boolean = login.matches(Regex("[a-z]{1,10}"))

    fun needAuthorization(): Boolean = res != ""

    fun checkResName(): Boolean = res.matches(Regex("[A-Z]+(|.[A-Z]+)+"))

    fun needAcc(): Boolean = ds != ""

    fun checkDate(): Boolean {
        return try {
            val timeStart = LocalDate.parse(ds)
            val timeEnd = LocalDate.parse(de)
            timeStart < timeEnd
        } catch (e: Exception) {
            false
        }
    }

    fun checkVol(): Boolean {
        return try {
            vol.toInt() > 0
        } catch (e: Exception) {
            false
        }
    }
}
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

    init {
        for (i in 0..7) {
            when (args[i]) {
                "-login" -> login = args[i + 1]
                "-pass" -> password = args[i + 1]
                "-role" -> role = args[i + 1]
                "-res" -> res = args[i + 1]
                "-ds" -> ds = args[i + 1]
                "-de" -> de = args[i + 1]
                "-vol" -> vol = args[i + 1]
            }
        }
    }


    fun CheckArg(): Boolean {
        if (arr.isEmpty()) {
            PrintHelp()
            return true
        }
        return false


    }

    fun PrintHelp() {
        print(Help)
    }

    val Help: String = "-h - вызов справки\n" +
            "        -login <str> -pass <str> - аутентификация s\n" +
            "        -login <str> -pass <str> -res <str> -role <str> - авторизация к введенному ресурсу\n" +
            "        -ds <YYYY-MM-DD> -de <YYYY-MM-DD> -vol <int> - занесение данных об использовании ресурса(только после авторизации"

    fun CheckHelp(): Boolean {
        if (arr[0] == "-h") {
            PrintHelp()
            return true
        }
        return false
    }

    fun NeedAuth(): Boolean = when {
        login != "" && password != "" -> true
        else -> false
    }

    fun ValidateLogin(): Boolean {

        return login.matches(Regex("[a-z]{1,10}"))

    }

    fun NeedAuthorization(): Boolean = when {
        res != "" -> true
        else -> false
    }

    fun CheckResName(): Boolean {

        return res.matches(Regex("[A-Z]+(|.[A-Z]+)+"))
    }


    fun NeedAcc(): Boolean = when {
        ds != "" -> true
        else -> false
    }

    fun CheckDate(): Boolean {
        return try {
            val timeStart = LocalDate.parse(ds)
            val timeEnd = LocalDate.parse(de)
            timeStart < timeEnd

        } catch (e: Exception) {
            false
        }


    }

    fun CheckVol(): Boolean {
        return try {
            vol.toInt() > 0
        } catch (e: Exception) {
            false
        }
    }


}
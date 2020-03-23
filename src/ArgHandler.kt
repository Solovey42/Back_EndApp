import enums.ExitCodes
import kotlin.system.exitProcess
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
        for (arg in args) {

            when (arg) {
                "-login" -> login = args[args.indexOf(arg) + 1]
                "-pass" -> password = args[args.indexOf(arg) + 1]
                "-role" -> role = args[args.indexOf(arg) + 1]
                "-res" -> res = args[args.indexOf(arg) + 1]
                "-ds" -> ds = args[args.indexOf(arg) + 1]
                "-de" -> de = args[args.indexOf(arg) + 1]
                "-vol" -> vol = args[args.indexOf(arg) + 1]
            }
        }
    }

    fun ChekArg() {
        if (arr.isEmpty()) {
            PrintHelp()
            exitProcess(ExitCodes.HelpCode.code)
        }


    }

    fun PrintHelp() {
        print(Help)
    }

    val Help: String = "-h - вызов справки\n" +
            "        -login <str> -pass <str> - аутентификация s\n" +
            "        -login <str> -pass <str> -res <str> -role <str> - авторизация к введенному ресурсу\n" +
            "        -ds <YYYY-MM-DD> -de <YYYY-MM-DD> -vol <int> - занесение данных об использовании ресурса(только после авторизации"

    fun ChekHelp() {
        if (arr[0] == "h") {
            PrintHelp()
            exitProcess(ExitCodes.HelpCode.code)
        }


    }

    fun NeedAuth(): Boolean {
        return when {
            login != "" && password != "" -> true
            else -> false
        }


    }

    fun ValidateLogin(): Boolean {

        return login.matches(Regex("[a-z]{1,10}"))

    }

    fun NeedAuthorization(): Boolean {
        return when {
            res != "" -> true
            else -> false
        }
    }

    fun CheckResName(): Boolean {

        return res.matches(Regex("[A-Z]+(|.[A-Z]+)+"))
    }


    fun NeedAcc(): Boolean {
        return when {
            ds != "" -> true
            else -> false
        }
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
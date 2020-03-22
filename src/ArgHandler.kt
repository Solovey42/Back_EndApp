import kotlin.system.exitProcess
import java.time.LocalDate
import java.time.format.DateTimeFormatter


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

    fun NeedAuthorization(): Boolean {
        return when {
            res != "" -> true
            else -> false
        }
    }

    fun CheckResName(): Boolean {
        val regex = Regex(pattern = "[A-Z]+(.[A-Z]+)+")
        return regex.containsMatchIn(input = res)
    }

    fun NeedAcc(): Boolean {
        return when {
            ds != "" -> true
            else -> false
        }
    }

    fun CheckDate(): Boolean {
        val timeStart = LocalDate.parse(ds)
        val timeEnd = LocalDate.parse(de)
        return timeStart < timeEnd
    }

    fun CheckVol(): Boolean {
        return vol.toInt() > 0
    }



}
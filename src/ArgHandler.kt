import kotlin.system.exitProcess

class ArgHandler(args: Array<String>) {
    private val arr: Array<String> = args
    val login: String = args[1]
    val password: String = args[3]
    val role: String = args[5]
    val res: String = args[7]


    fun ChekArg(): Int {
        if (arr.isEmpty())
            exitProcess(0)
        else
            exitProcess(1)
    }

    private fun PrintHelp() {
        print(Help)
    }

    val Help: String = "-h - вызов справки\n" +
            "        -login <str> -pass <str> - аутентификация s\n" +
            "        -login <str> -pass <str> -res <str> -role <str> - авторизация к введенному ресурсу\n" +
            "        -ds <YYYY-MM-DD> -de <YYYY-MM-DD> -vol <int> - занесение данных об использовании ресурса(только после авторизации"

    fun ChekHelp() {
        if (arr[0] == "-h")
            PrintHelp()
        else
            exitProcess(0)
    }

    fun NeedAuth(): Boolean {
        return when {
            arr[0] == "-login" && arr[2] == "-pass" -> true
            else -> false
        }

    }

    fun NeedAuthorization(): Boolean {
        return when {
            arr[4] == "-res" -> true
            else -> false
        }
    }

    fun CheckResName(): Boolean {
        val regex = Regex(pattern = "[A-Z]+(.[A-Z]+)+")
        return regex.containsMatchIn(input = res)
    }

    fun NeedAcc(): Boolean {
        return when {
            arr[6] == "-ds" -> true
            else -> false
        }
    }


}
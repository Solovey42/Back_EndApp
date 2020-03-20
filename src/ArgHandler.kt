import kotlin.system.exitProcess

class ArgHandler(args:Array<String>) {
    val login:String = args[1]
    val password:String = args[3]

    fun ChekArg(args: Array<String>):Int
    {
        if(args.isEmpty())
            exitProcess(0)
        else
            exitProcess(1)
    }
    fun PrintHelp()
    {
        print(Help)
    }
    val Help:String = "-h - вызов справки\n" +
            "        -login <str> -pass <str> - аутентификация s\n" +
            "        -login <str> -pass <str> -res <str> -role <str> - авторизация к введенному ресурсу\n" +
            "        -ds <YYYY-MM-DD> -de <YYYY-MM-DD> -vol <int> - занесение данных об использовании ресурса(только после авторизации"
    fun ChekHelp(args: Array<String>)
    {
        if(args[0]=="-h")
            PrintHelp()
        else
            exitProcess(0)
    }
    fun NeedAuth(args: Array<String>) {
        if (args[0] == "-login" && args[2] == "-pass")
            exitProcess(0)

    }


}
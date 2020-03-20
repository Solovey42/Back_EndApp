import kotlin.system.exitProcess

fun main(args:Array<String>)
{
    ChekArg(args)
}
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
const val Help:String = "-h - вызов справки\n" +
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
fun ValidateLogin(args: Array<String>){
    val regex = Regex(pattern = "[a-z]{1,10}")
    val matched = regex.containsMatchIn(input = "vasya")
}
fun CheckLogin(args: Array<String>)
{
    if(Users.contains(Users.find { it.login == args[1] }))
        exitProcess(0)
    else
        exitProcess(3)

}

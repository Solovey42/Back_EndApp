import kotlin.system.exitProcess

fun main(args:Array<String>)
{

}
fun ValidateLogin(args: Array<String>){

    val regex = Regex(pattern = "[a-z]{1,10}")
    val matched = regex.containsMatchIn(input = args[1])
    if(matched==true)
        exitProcess(0)
    else
        exitProcess(2)
}
fun CheckLogin(args: Array<String>)
{
    if(Users.contains(Users.find { it.login == args[1] }))
        exitProcess(0)
    else
        exitProcess(3)
}
fun CheckLoinPass(args: Array<String>)
{

    if(Users[Users.indexOf(Users.find { it.login == args[3] })].pass == args [3])
        exitProcess(0)
    else
        exitProcess(4)
}

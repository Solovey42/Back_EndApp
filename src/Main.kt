import kotlin.system.exitProcess

fun main(args:Array<String>)
{
    val argHandler = ArgHandler(args)


    fun ValidateLogin(){

        val regex = Regex(pattern = "[a-z]{1,10}")
        val matched = regex.containsMatchIn(input = argHandler.login)
        if(matched==true)
            exitProcess(0)
        else
            exitProcess(2)
    }
    fun CheckLogin(args: Array<String>)
    {
        if(Users.contains(Users.find { it.login == argHandler.login }))
            exitProcess(0)
        else
            exitProcess(3)
    }
    fun CheckLoinPass(args: Array<String>)
    {

        if(Users[Users.indexOf(Users.find { it.login == argHandler.login })].pass == argHandler.password)
            exitProcess(0)
        else
            exitProcess(4)
    }


}


import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val argHandler = ArgHandler(args)
    CheckResRole(argHandler)


}

fun ValidateLogin(argHandler: ArgHandler): Boolean {

    val regex = Regex(pattern = "[a-z]{1,10}")
    return regex.containsMatchIn(input = argHandler.login)
}

fun CheckLogin(argHandler: ArgHandler) {
    if (Users.contains(Users.find { it.login == argHandler.login }))
        exitProcess(0)
    else
        exitProcess(3)
}

fun CheckLoinPass(argHandler: ArgHandler) {

    if (Users[Users.indexOf(Users.find { it.login == argHandler.login })].pass == argHandler.password)
        exitProcess(0)
    else
        exitProcess(4)
}

fun CheckResRole(argHandler: ArgHandler):Boolean {
    if (argHandler.role != Roles.EXECUTE.name && argHandler.role != Roles.READ.name && argHandler.role != Roles.WRITE.name)
        exitProcess(5)
    else if (Resources[Resources.indexOf(Resources.find { it.user.login == argHandler.login && it.res == argHandler.res })].role.name == argHandler.role)
        exitProcess(0)
    else
        exitProcess(6)

}



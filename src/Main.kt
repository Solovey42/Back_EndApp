import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val argHandler = ArgHandler(args)

}

fun ValidateLogin(argHandler: ArgHandler): Boolean {

    return argHandler.login.matches(Regex("[a-z]{1,10}"))

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


fun CheckResRole(argHandler: ArgHandler): Boolean {
    if (argHandler.role != Roles.EXECUTE.name && argHandler.role != Roles.READ.name && argHandler.role != Roles.WRITE.name)
        exitProcess(5)
    val resources = Resources.find { it.user.login == argHandler.login }

    val nodes = argHandler.res.split(".")
    for (index in nodes.indices) {
        val currentNode = nodes.subList(0, index + 1).joinToString(".")
        if (Resources.any { it.res == currentNode && it.role.name == argHandler.role })
            exitProcess(0)
    }

    exitProcess(6)
}

fun AddSession(argHandler: ArgHandler) {
   /// Session(Users[Users.indexOf(Users.find { it.login == argHandler.login })], Resources[Resources.indexOf(Resources.find { it.res == argHandler.res })], LocalDate.parse(argHandler.ds), LocalDate.parse(argHandler.ds), argHandler.vol.toInt())
}




import java.time.LocalDate
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val argHandler = ArgHandler(args)
    argHandler.ChekArg()
    argHandler.ChekHelp()
    val auth = Authentication(argHandler)
    val authorization = Authorization(argHandler)


}






fun AddSession(argHandler: ArgHandler) {
    Session(Users[Users.indexOf(Users.find { it.login == argHandler.login })], Resources[Resources.indexOf(Resources.find { it.res == argHandler.res })], LocalDate.parse(argHandler.ds), LocalDate.parse(argHandler.ds), argHandler.vol.toInt())
}




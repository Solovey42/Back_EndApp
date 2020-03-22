import java.time.LocalDate
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val argHandler = ArgHandler(args)
    argHandler.ChekArg()
    argHandler.ChekHelp()
    val auth = Authentication(argHandler)
    val authorization = Authorization(argHandler)


}











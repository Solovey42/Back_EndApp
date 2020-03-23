import java.time.LocalDate
import kotlin.system.exitProcess

class Accounting(argHandler: ArgHandler) {
    init {
        start(argHandler)
    }

    private fun start(argHandler: ArgHandler) {
        if (argHandler.NeedAcc())
            if (argHandler.CheckDate())
                if (argHandler.CheckVol()) {
                    AddSession(argHandler)
                    exitProcess(ExitCodes.Success.code)
                } else exitProcess(ExitCodes.IncorrectActivity.code)
            else exitProcess(ExitCodes.IncorrectActivity.code)
    }

    private fun AddSession(argHandler: ArgHandler) {
        val session = Session(Users[Users.indexOf(Users.find { it.login == argHandler.login })], Resources[Resources.indexOf(Resources.find { it.res == argHandler.res })], LocalDate.parse(argHandler.ds), LocalDate.parse(argHandler.ds), argHandler.vol.toInt())
        Sessions.add(session)


    }
}
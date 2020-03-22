import kotlin.system.exitProcess

class Accounting(argHandler: ArgHandler) {
    init {
        start(argHandler)
    }

    private fun start(argHandler: ArgHandler) {
        if (argHandler.NeedAcc())
            if (argHandler.CheckDate())
                if (argHandler.CheckVol())
                    AddSession(argHandler)
                else exitProcess(ExitCodes.IncorrectActivity.code)
            else exitProcess(ExitCodes.IncorrectActivity.code)
        else exitProcess(ExitCodes.Success.code)

    }
}
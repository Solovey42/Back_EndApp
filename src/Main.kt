fun main(args: Array<String>) {

    val argHandler = ArgHandler(args)
    argHandler.ChekArg()
    argHandler.ChekHelp()
    Authentication(argHandler)
    Authorization(argHandler)






}











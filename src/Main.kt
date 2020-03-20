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
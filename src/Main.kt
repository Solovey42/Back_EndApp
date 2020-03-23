fun main(args: Array<String>) {

    val argHandler = ArgHandler(args)
    argHandler.ChekArg()
    argHandler.ChekHelp()
    Authentication(argHandler)


}

var Users: List<User> = listOf(User("vasya", "123"), User("admin", "admin"), User("q", "?!@"), User("abcd", "qwerty"))

var Resources: List<Resource> = listOf(Resource("A", Roles.READ, Users[0]), Resource("A.B.C", Roles.WRITE, Users[0]), Resource("A.B", Roles.EXECUTE, Users[1]), Resource("A", Roles.READ, Users[1]), Resource("A.B", Roles.WRITE, Users[1]), Resource("A.B.C", Roles.READ, Users[1]), Resource("B", Roles.EXECUTE, Users[2]), Resource("A.A.A", Roles.EXECUTE, Users[0]))

var Sessions: MutableList<Session> = mutableListOf()











import enums.Roles
import models.Resource
import models.Session
import models.User
import services.Authentication

fun main(args: Array<String>) {


    val argHandler = ArgHandler(args)
    argHandler.ChekArg()
    argHandler.ChekHelp()
    Authentication(argHandler)


}

var Users: List<User> = listOf(User("vasya", "123", "0a4b21dd234f89b25e9a39ed87273253dc1840ccf23ee2a90380c698280f5338", "dad1ef245"), User("admin", "admin", "d6fed044a4457acf2437d68189251a6d07918c9d7345421ec5c0a2f1043766a7", "dke94jgj4"), User("q", "?!@", "580cbc37144acf152ca48ca59f6cc5b1fbc2a53b947cdf8c29939e979a030900", "djrl409fj"), User("abcd", "qwerty", "6f80cf73456754822a8c47261f488fc1c57267eab2981e360797b9876849b0f3", "dlk4u9f39"))

var Resources: List<Resource> = listOf(Resource("A", Roles.READ, Users[0]), Resource("A.B.C", Roles.WRITE, Users[0]), Resource("A.B", Roles.EXECUTE, Users[1]), Resource("A", Roles.READ, Users[1]), Resource("A.B", Roles.WRITE, Users[1]), Resource("A.B.C", Roles.READ, Users[1]), Resource("B", Roles.EXECUTE, Users[2]), Resource("A.A.A", Roles.EXECUTE, Users[0]))

var Sessions: MutableList<Session> = mutableListOf()










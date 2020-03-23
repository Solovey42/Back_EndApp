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

var Users: List<User> = listOf(User("vasya", "123","a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3","dad1ef245"), User("admin", "admin","8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918","dke94jgj4"), User("q", "?!@","611428b8a9db97cc5210a3450aaea01d4218bc2aa7a466fb91f007ba501911ca","djrl409fj"), User("abcd", "qwerty","65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5","dlk4u9f39"))

var Resources: List<Resource> = listOf(Resource("A", Roles.READ, Users[0]), Resource("A.B.C", Roles.WRITE, Users[0]), Resource("A.B", Roles.EXECUTE, Users[1]), Resource("A", Roles.READ, Users[1]), Resource("A.B", Roles.WRITE, Users[1]), Resource("A.B.C", Roles.READ, Users[1]), Resource("B", Roles.EXECUTE, Users[2]), Resource("A.A.A", Roles.EXECUTE, Users[0]))

var Sessions: MutableList<Session> = mutableListOf()











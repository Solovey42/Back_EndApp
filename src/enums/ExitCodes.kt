package enums

enum class ExitCodes(val code: Int) {
    Success(0),
    HelpCode(1),
    InvalidLoginFormat(2),
    UnknownLogin(3),
    InvalidPassword(4),
    UnknownRole(5),
    NoAccess(6),
    IncorrectActivity(7), ;



}
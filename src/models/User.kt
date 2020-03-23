package models

data class User(val login:String, val pass:String, val hash:String, val salt: String)
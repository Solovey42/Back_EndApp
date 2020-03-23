package models

import enums.Roles

data class Resource(val res: String, val role: Roles, val user: User)
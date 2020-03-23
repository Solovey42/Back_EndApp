package Models

import Enums.Roles

data class Resource(val res: String, val role: Roles, val user: User)
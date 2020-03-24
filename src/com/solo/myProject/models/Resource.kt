package com.solo.myProject.models

import com.solo.myProject.enums.Roles

data class Resource(val res: String, val role: Roles, val user: User)
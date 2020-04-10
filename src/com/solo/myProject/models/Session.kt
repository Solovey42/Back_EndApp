package com.solo.myProject.models

import java.time.LocalDate

data class Session(val user: User, val res: String, val ds: LocalDate, val de: LocalDate, val vol: Int)
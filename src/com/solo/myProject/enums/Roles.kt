package com.solo.myProject.enums

enum class Roles {
    READ, WRITE, EXECUTE;

    companion object {
        fun check(role: String) = values().map { it.name }.contains(role)
    }
}
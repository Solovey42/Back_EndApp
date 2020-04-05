package com.solo.myProject.enums

enum class Roles {
    READ, WRITE, EXECUTE;

    companion object {
        fun check(role: String): Roles? {
            return if(values().map { it.name }.contains(role))
                valueOf(role.toUpperCase())
            else
                null
        }
    }

}
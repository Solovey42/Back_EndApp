package com.solo.myProject.enums

enum class Roles {
    READ, WRITE, EXECUTE;

    companion object {
        fun check() = values().map { it.name }
    }

}
package com.solo.myProject.models

data class User(val login: String, val hash: String, val salt: String)
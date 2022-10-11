package com.example.assignment5
import java.io.Serializable

data class  User(val firstName: String, val lastName: String, val username: String, var password: String): Serializable { }
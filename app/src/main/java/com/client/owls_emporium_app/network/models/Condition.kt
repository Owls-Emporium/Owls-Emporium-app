package com.client.owls_emporium_app.network.models

import com.google.gson.Gson

class Condition(
    val id: String,
    val name: String,
    val image: String

) {
    override fun toString(): String {
        return name
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}
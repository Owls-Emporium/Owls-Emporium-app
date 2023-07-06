package com.client.owls_emporium_app.network.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("id") val id: String?= null,
    @SerializedName("name") var name: String,
    @SerializedName("lastname") var lastname: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") var phone: String,
    @SerializedName("password") val password: String,
    @SerializedName("image") var image: String?= null,
    @SerializedName("session_token") val sessionToken: String?= null,
    @SerializedName("roles") val roles: String?= null
        ){
    override fun toString(): String {
        return "$name $lastname"
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}
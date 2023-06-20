package com.client.owls_emporium_app.network.models

import com.google.gson.annotations.SerializedName

class Rol (
    @SerializedName("id") val id: String,
    @SerializedName("name") val rol: String,
    @SerializedName("image") val image: String,
        ){
    override fun toString(): String {
        return  "Rol(id='$id', rol='$rol',image='$image')"
    }
}
package com.client.owls_emporium_app.network.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Suggest(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("image1") val image1: String? = null,
    @SerializedName("price") val price: Double
    ) {

    override fun toString(): String {
        return "Product(id='$id', name='$name', image1='$image1', price=$price)"
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}
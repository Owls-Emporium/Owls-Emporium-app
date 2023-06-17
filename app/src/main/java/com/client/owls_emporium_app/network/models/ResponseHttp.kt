package com.client.owls_emporium_app.network.models

import com.google.gson.annotations.SerializedName
import com.google.gson.JsonObject

class ResponseHttp (
    @SerializedName("message") val message: String,
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("data") val data: JsonObject,
    @SerializedName("error") val error: String,
        ) {
    override fun toString(): String {
        return "ResponseHttp( message='$message', isSuccess=$isSuccess, data=$data, error='$error')"
    }
}
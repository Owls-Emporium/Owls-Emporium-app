package com.client.owls_emporium_app.network.routes

import com.client.owls_emporium_app.network.models.Category
import com.client.owls_emporium_app.network.models.Condition
import com.client.owls_emporium_app.network.models.ResponseHttp
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ConditionRoutes {

    @GET("condition/getAll")
    fun getAll(
        @Header("Authorization") token: String
    ): Call<ArrayList<Condition>>

    @Multipart
    @POST("condition/create")
    fun create(
        @Part image: MultipartBody.Part,
        @Part("condition") condition: RequestBody,
        @Header("Authorization") token: String
    ): Call<ResponseHttp>

}
package com.client.owls_emporium_app.network.routes

import com.client.owls_emporium_app.network.models.Product
import com.client.owls_emporium_app.network.models.ResponseHttp
import com.client.owls_emporium_app.network.models.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.Locale.Category

interface SuggestRoutes {

   @GET("products/getAll")
  fun getAll(
       @Header("Authorization") token: String
   ): Call<ArrayList<Product>>


}
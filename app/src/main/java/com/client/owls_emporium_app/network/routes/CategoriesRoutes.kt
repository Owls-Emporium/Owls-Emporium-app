package com.client.owls_emporium_app.network.routes

import com.client.owls_emporium_app.network.models.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CategoriesRoutes {

    @GET("categories/getAll")
    fun getAll(
        @Header("Authorization") token: String
    ): Call<ArrayList<Category>>

}
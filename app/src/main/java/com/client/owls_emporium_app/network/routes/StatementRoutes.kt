package com.client.owls_emporium_app.network.routes

import com.client.owls_emporium_app.network.models.Statement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface StatementRoutes {

    @GET("statement/getAll")
    fun getAll(
        @Header("Authorization") token: String
    ): Call<ArrayList<Statement>>

}
package com.client.owls_emporium_app.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofitclient {

    //nos permite enviar las peticiones al servidor
    fun getClient(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
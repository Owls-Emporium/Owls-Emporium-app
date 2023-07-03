package com.client.owls_emporium_app.network.api

import com.client.owls_emporium_app.network.routes.UsersRoutes
//192.168.1.21
//failed to connect to, yes chanche ip
class ApiRoutes {

    //val API_URL = "http://192.168.1.21:3001/api/"
    val API_URL = " https://owlsemporium-db-55d197288819.herokuapp.com/api/"
    val retrofit = Retrofitclient()

    fun getUsersRoutes(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }

}
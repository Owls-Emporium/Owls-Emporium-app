package com.client.owls_emporium_app.network.providers

import com.client.owls_emporium_app.network.api.ApiRoutes
import com.client.owls_emporium_app.network.models.ResponseHttp
import com.client.owls_emporium_app.network.models.User
import com.client.owls_emporium_app.network.routes.UsersRoutes
import retrofit2.Call

class UsersProvider {

    private var usersRoutes: UsersRoutes?= null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }
    //? nos dice que tambien puede retornar un nulo
    fun register(user: User): Call<ResponseHttp>?{
        return usersRoutes?.register(user)
    }

    fun login(email: String, password: String): Call<ResponseHttp>?{
        return usersRoutes?.login(email, password)
    }

}
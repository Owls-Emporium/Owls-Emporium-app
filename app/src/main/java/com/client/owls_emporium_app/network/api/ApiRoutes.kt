package com.client.owls_emporium_app.network.api

import com.client.owls_emporium_app.network.routes.CategoriesRoutes
import com.client.owls_emporium_app.network.routes.ConditionRoutes
import com.client.owls_emporium_app.network.routes.ProductsRoutes
import com.client.owls_emporium_app.network.routes.StatementRoutes
import com.client.owls_emporium_app.network.routes.SuggestRoutes
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
    fun getUsersRoutesWithToken(token: String): UsersRoutes {
        return retrofit.getClientWithToken(API_URL, token).create(UsersRoutes::class.java)
    }

    fun getCategoriesRoutes(token: String): CategoriesRoutes {
        return retrofit.getClientWithToken(API_URL, token).create(CategoriesRoutes::class.java)
    }

    fun getConditionRoutes(token: String): ConditionRoutes {
        return retrofit.getClientWithToken(API_URL, token).create(ConditionRoutes::class.java)
    }

    fun getStatementRoutes(token: String): StatementRoutes {
        return retrofit.getClientWithToken(API_URL, token).create(StatementRoutes::class.java)
    }

    fun getProductsRoutes(token: String): ProductsRoutes {
        return retrofit.getClientWithToken(API_URL, token).create(ProductsRoutes::class.java)
    }




}
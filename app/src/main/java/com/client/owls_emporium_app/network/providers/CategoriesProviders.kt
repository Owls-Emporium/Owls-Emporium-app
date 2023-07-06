package com.client.owls_emporium_app.network.providers

import com.client.owls_emporium_app.network.api.ApiRoutes
import com.client.owls_emporium_app.network.models.Category
import com.client.owls_emporium_app.network.routes.CategoriesRoutes
import retrofit2.Call

class CategoriesProviders(val token: String) {

    private var categoriesRoutes: CategoriesRoutes? = null

    init {
        val api = ApiRoutes()
        categoriesRoutes = api.getCategoriesRoutes(token)
    }

    fun getAll(): Call<ArrayList<Category>>? {
        return categoriesRoutes?.getAll(token)
    }

}
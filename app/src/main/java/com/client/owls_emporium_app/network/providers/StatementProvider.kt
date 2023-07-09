package com.client.owls_emporium_app.network.providers

import com.client.owls_emporium_app.network.api.ApiRoutes
import com.client.owls_emporium_app.network.models.Statement
import com.client.owls_emporium_app.network.routes.StatementRoutes
import retrofit2.Call

class StatementProvider (val token: String){

    private var statementRoutes: StatementRoutes? = null

    init {
        val api = ApiRoutes()
        statementRoutes = api.getStatementRoutes(token)
    }
    fun getAll(): Call<ArrayList<Statement>>? {
        return statementRoutes?.getAll(token)
    }
}
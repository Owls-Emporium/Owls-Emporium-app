package com.client.owls_emporium_app.network.providers

import android.util.Log
import com.client.owls_emporium_app.network.api.ApiRoutes
import com.client.owls_emporium_app.network.models.Category
import com.client.owls_emporium_app.network.models.ResponseHttp
import com.client.owls_emporium_app.network.routes.CategoriesRoutes
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class CategoriesProviders(val token: String) {

    private var categoriesRoutes: CategoriesRoutes? = null

    init {
        val api = ApiRoutes()
        categoriesRoutes = api.getCategoriesRoutes(token)
    }

    fun getAll(): Call<ArrayList<Category>>? {
        return categoriesRoutes?.getAll(token)
    }

    fun create(file: File, category: Category): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val image = MultipartBody.Part.createFormData("image", file.name, reqFile)

        Log.d("CATEGORY", category.toJson())

        val requestBody = RequestBody.create(MediaType.parse("text/plain"), category.toJson())
        return categoriesRoutes?.create(image, requestBody, token)
    }

}
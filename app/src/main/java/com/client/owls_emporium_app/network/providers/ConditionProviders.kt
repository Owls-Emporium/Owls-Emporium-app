package com.client.owls_emporium_app.network.providers

import android.util.Log
import com.client.owls_emporium_app.network.api.ApiRoutes
import com.client.owls_emporium_app.network.models.Condition
import com.client.owls_emporium_app.network.models.ResponseHttp
import com.client.owls_emporium_app.network.routes.CategoriesRoutes
import com.client.owls_emporium_app.network.routes.ConditionRoutes
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class ConditionProviders(val token: String) {

    private var conditionRoutes: ConditionRoutes? = null

    init {
        val api = ApiRoutes()
        conditionRoutes = api.getConditionRoutes(token)
    }

    fun getAll(): Call<ArrayList<Condition>>? {
        return conditionRoutes?.getAll(token)
    }



    fun create(file: File, condition: Condition): Call<ResponseHttp>? {
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val image = MultipartBody.Part.createFormData("image", file.name, reqFile)

        Log.d("CONDITION", condition.toJson())

        val requestBody = RequestBody.create(MediaType.parse("text/plain"), condition.toJson())
        return conditionRoutes?.create(image, requestBody, token)
    }

}
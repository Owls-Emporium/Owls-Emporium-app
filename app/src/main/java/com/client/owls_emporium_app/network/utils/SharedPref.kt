package com.client.owls_emporium_app.network.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import java.lang.Exception

//para guardar la seccion del usuario
class SharedPref(activity: Activity) {

    private var prefs: SharedPreferences ?= null

    init{
        prefs = activity.getSharedPreferences("com.client.owls_emporium_app",Context.MODE_PRIVATE)
    }
    //almacena la session
    fun save(key: String, objeto:Any){
        try {
            val gson = Gson()
            val json = gson.toJson(objeto)
            with(prefs?.edit()){
                this?.putString(key,json)
                this?.commit()
            }
        }catch (e: Exception){
            Log.d("ERROR","Error: ${e.message}")
        }
    }

    fun getData(key: String): String? {
        val data = prefs?. getString(key,"")
        return data
    }

    fun remove(key: String){
        prefs?.edit()?.remove(key)?.apply()
    }
}
package com.client.owls_emporium_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.network.adapters.SaveProductsAdapter
import com.client.owls_emporium_app.network.models.Product
import com.client.owls_emporium_app.network.utils.SharedPref
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SaveProductsActivity : AppCompatActivity() {

    var recyclerViewSaveProducts: RecyclerView? = null
    var toolbar: Toolbar? = null
    var adapter: SaveProductsAdapter? = null
    var sharedPref: SharedPref? = null
    var gson = Gson()
    var selectedProducts = ArrayList<Product>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_products)

        sharedPref = SharedPref(this)

        recyclerViewSaveProducts = findViewById(R.id.recyclerview_save_products)
        toolbar = findViewById(R.id.toolbar)

        toolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar?.title = "Tus productos guardados"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerViewSaveProducts?.layoutManager = LinearLayoutManager(this)

        getProductsFromSharedPref()

    }


    private fun getProductsFromSharedPref() {

        if (!sharedPref?.getData("order").isNullOrBlank()) { // EXISTE UNA ORDEN EN SHARED PREF
            val type = object: TypeToken<ArrayList<Product>>() {}.type
            selectedProducts = gson.fromJson(sharedPref?.getData("order"), type)

            adapter = SaveProductsAdapter(this, selectedProducts)
            recyclerViewSaveProducts?.adapter = adapter
        }

    }
}
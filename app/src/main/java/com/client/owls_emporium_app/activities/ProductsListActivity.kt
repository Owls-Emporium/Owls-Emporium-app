package com.client.owls_emporium_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.network.adapters.ProductsAdapter
import com.client.owls_emporium_app.network.models.Product
import com.client.owls_emporium_app.network.models.User
import com.client.owls_emporium_app.network.providers.ProductsProvider
import com.client.owls_emporium_app.network.utils.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsListActivity : AppCompatActivity() {

    val TAG = "ProductsList"
    var recyclerViewProducts: RecyclerView? = null
    var adapter: ProductsAdapter? = null

    var user: User? = null
    var sharedPref: SharedPref? = null

    var productsProvider: ProductsProvider? = null
    var products: ArrayList<Product> = ArrayList()

    var idCategory: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)

        sharedPref = SharedPref(this)

        idCategory = intent.getStringExtra("idCategory")
        getUserFromSession()


        productsProvider = ProductsProvider(user?.sessionToken!!)

        recyclerViewProducts = findViewById(R.id.recyclerview_products_new)
        recyclerViewProducts?.layoutManager = GridLayoutManager(this,2)

        getProducts()
    }

    private fun getUserFromSession() {
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            //si exite el usuario en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }

    private fun getProducts(){
        productsProvider?.findByCategory(idCategory!!)?.enqueue(object:
            Callback<ArrayList<Product>> {
            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                if (response.body() != null){
                    products = response.body()!!
                    adapter = ProductsAdapter(this@ProductsListActivity, products)
                    recyclerViewProducts?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Toast.makeText(this@ProductsListActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Error: ${t.message}")
            }

        })
    }
}
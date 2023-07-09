package com.client.owls_emporium_app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.network.adapters.CategoriesAdapter
import com.client.owls_emporium_app.network.adapters.ProductsAdapter
import com.client.owls_emporium_app.network.models.Category
import com.client.owls_emporium_app.network.models.Product
import com.client.owls_emporium_app.network.models.User
import com.client.owls_emporium_app.network.providers.CategoriesProviders
import com.client.owls_emporium_app.network.providers.ProductsProvider
import com.client.owls_emporium_app.network.utils.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPageFragment : Fragment() {

    val  TAG = "CategoriesFragment"
    var myView: View? = null
    var recyclerViewCategories: RecyclerView? = null
    var categoriesProvider: CategoriesProviders? = null
    var adapter: CategoriesAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
    var categories = ArrayList<Category>()

    //NUEVOS
    var recyclerViewProducts: RecyclerView? = null
    var adapters: ProductsAdapter? = null
    var productsProvider: ProductsProvider? = null
    var products: ArrayList<Product> = ArrayList()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_main_page, container, false)

        recyclerViewCategories = myView?.findViewById(R.id.recyclerview_category)
        recyclerViewCategories?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false) // Elementos se mostraran de manera vertical
        sharedPref = SharedPref(requireActivity())

        getUserFromSession()

        categoriesProvider = CategoriesProviders(user?.sessionToken!!)


        productsProvider = ProductsProvider(user?.sessionToken!!)

        recyclerViewProducts = myView?.findViewById(R.id.recyclerview_products_suggest)
        recyclerViewProducts?.layoutManager =  LinearLayoutManager(requireContext(),)

        getCategories()
        getProducts()


        return myView
    }

    private fun getCategories() {
        categoriesProvider?.getAll()?.enqueue(object: Callback<ArrayList<Category>> {
            override fun onResponse(call: Call<ArrayList<Category>>, response: Response<ArrayList<Category>>
            ) {
                if (response.body() != null) {
                    categories = response.body()!!
                    adapter = CategoriesAdapter(requireActivity(), categories)
                    recyclerViewCategories?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Category>>, t: Throwable) {
                Log.d(TAG, " Error: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getProducts(){
        productsProvider?.getAll()?.enqueue(object:
            Callback<ArrayList<Product>> {
            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                if (response.body() != null){
                    products = response.body()!!
                    adapters = ProductsAdapter(requireActivity(), products)
                    recyclerViewProducts?.adapter = adapters
                }
            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Log.d(TAG, " Error: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getUserFromSession() {
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            //si exite el usuario en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }

}


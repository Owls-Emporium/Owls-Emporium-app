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
import com.client.owls_emporium_app.network.adapters.ConditionAdapter
import com.client.owls_emporium_app.network.models.Category
import com.client.owls_emporium_app.network.models.Condition
import com.client.owls_emporium_app.network.models.User
import com.client.owls_emporium_app.network.providers.CategoriesProviders
import com.client.owls_emporium_app.network.providers.ConditionProviders
import com.client.owls_emporium_app.network.utils.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConditionFragment : Fragment() {

    val  TAG = "ConditionFragment"
    var myView: View? = null
    var recyclerViewCondition: RecyclerView? = null
    var conditionProvider: ConditionProviders? = null
    var adapter: ConditionAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
    var condition= ArrayList<Condition>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_condition, container, false)

        recyclerViewCondition = myView?.findViewById(R.id.recyclerview_condition)
        recyclerViewCondition?.layoutManager = LinearLayoutManager(requireContext()) // Elementos se mostraran de manera vertical
        sharedPref = SharedPref(requireActivity())

        getUserFromSession()

        conditionProvider = ConditionProviders(user?.sessionToken!!)

        getCondition()

    return myView
    }

    private fun getCondition() {
        conditionProvider?.getAll()?.enqueue(object: Callback<ArrayList<Condition>> {
            override fun onResponse(call: Call<ArrayList<Condition>>, response: Response<ArrayList<Condition>>
            ) {
                if (response.body() != null) {
                    condition = response.body()!!
                    adapter = ConditionAdapter(requireActivity(), condition)
                    recyclerViewCondition?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Condition>>, t: Throwable) {
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


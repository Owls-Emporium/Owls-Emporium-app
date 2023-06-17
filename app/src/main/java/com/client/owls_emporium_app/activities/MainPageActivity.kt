package com.client.owls_emporium_app.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.fragments.MainPageFragment
import com.client.owls_emporium_app.fragments.NewProductFragment
import com.client.owls_emporium_app.fragments.ProfileFragment
import com.client.owls_emporium_app.network.models.User
import com.client.owls_emporium_app.network.utils.SharedPref
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainPageActivity : AppCompatActivity() {

    private val TAG ="MainPageActivity"
    var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        getUserFromSession()
        openFragment(MainPageFragment())

        bottomNavigation = findViewById(R.id.btn_navigation)
        bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    openFragment(MainPageFragment())
                    true
                }

                R.id.item_new_post -> {
                    openFragment(NewProductFragment())
                    true
                }

                R.id.item_profile -> {
                    openFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }


    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    //para mantener la sesion activa
    private fun getUserFromSession(){

        val sharedPref =SharedPref(this)
        val gson =Gson()

        if(!sharedPref.getData("user").isNullOrBlank()){
            //si el usuario exite en sesion
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)
            Log.d(TAG,"Usuario: $user")
        }
    }
}
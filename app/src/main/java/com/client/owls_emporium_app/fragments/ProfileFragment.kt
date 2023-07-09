package com.client.owls_emporium_app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.client.owls_emporium_app.R
import com.bumptech.glide.Glide
import com.client.owls_emporium_app.activities.MainActivity
import com.client.owls_emporium_app.activities.SaveProductsActivity
import com.client.owls_emporium_app.activities.UpdateProfileActivity
import com.client.owls_emporium_app.network.models.User
import com.client.owls_emporium_app.network.utils.SharedPref
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {

    var myview: View?= null
    var buttonUpdateProfile: Button ?= null
    var circleImageUser: CircleImageView?= null
    var textViewName: TextView?= null
    var textViewEmail: TextView ?= null
    var textViewPhone: TextView ?= null
    var buttonLogout: Button ?= null
    var sharedPref: SharedPref ?= null
    var user: User?= null

    var toolbar: Toolbar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_profile, container, false)


        setHasOptionsMenu(true)

        toolbar = myview?.findViewById(R.id.toolbar)
        toolbar?.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        toolbar?.title = "Bienvenido a tu perfil"
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        sharedPref = SharedPref(requireActivity())
        buttonUpdateProfile = myview?.findViewById(R.id.btn_update_profile)
        textViewName = myview?.findViewById(R.id.textview_name1)
        textViewEmail = myview?.findViewById(R.id.textview_email1)
        textViewPhone = myview?.findViewById(R.id.textview_phone1)
        circleImageUser = myview?.findViewById(R.id.circleimage_user)
        buttonLogout = myview?.findViewById(R.id.btn_logout)

        buttonLogout?.setOnClickListener{ logout() }
        buttonUpdateProfile?.setOnClickListener{ goToUpdate() }

        getUserFromSession()

        textViewName?.text = "${user?.name} ${user?.lastname}"
        textViewEmail?.text = user?.email
        textViewPhone?.text = user?.phone

        if (!user?.image.isNullOrBlank()) {
            Glide.with(requireContext()).load(user?.image).into(circleImageUser!!)
        }

        return myview
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_products_favorites,menu)

        super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_shopping_bag){
            goToSaveProducts()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToSaveProducts(){
        val i = Intent(requireContext(), SaveProductsActivity::class.java)
        startActivity(i)
    }

    private fun logout() {
        sharedPref?.remove("user")
        val i = Intent(requireActivity(), MainActivity::class.java)
        startActivity(i)
    }

    //obtiene los datos de session
    private fun getUserFromSession() {
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            //si exite el usuario en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }

    private fun goToUpdate() {
        val i = Intent(requireContext(), UpdateProfileActivity::class.java)
        startActivity(i)
    }
}
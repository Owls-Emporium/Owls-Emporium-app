package com.client.owls_emporium_app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.activities.UpdateProfileActivity

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
        val boton = view?.findViewById<Button>(R.id.btn_update_profile)

        //configuranod el click listener del boton
        boton?.setOnClickListener {
            // Aquí se llama al Activity
            val intent = Intent(activity, UpdateProfileActivity::class.java)
            startActivity(intent)
        }

        return view

    }



}
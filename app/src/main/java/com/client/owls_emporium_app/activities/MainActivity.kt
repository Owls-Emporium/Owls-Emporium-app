package com.client.owls_emporium_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.client.owls_emporium_app.R

class MainActivity : AppCompatActivity() {

    var btn_register: Button? = null
    var btn_home: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_register = findViewById(R.id.btn_register)
        btn_register?.setOnClickListener{ goToRegister() }
        btn_home = findViewById(R.id.btn_home)
        btn_home?.setOnClickListener{ goToMainPage() }
    }

    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    private fun goToMainPage() {
        val i = Intent(this, MainPageActivity::class.java)
        startActivity(i)
    }

}


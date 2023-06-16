package com.client.owls_emporium_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.client.owls_emporium_app.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var btn_login: Button? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_login = findViewById(R.id.btn_login)
        btn_login?.setOnClickListener{ goToLogin() }

    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}
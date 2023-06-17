package com.client.owls_emporium_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.client.owls_emporium_app.R

class MainActivity : AppCompatActivity() {

    var btn_register: Button? = null
    var btn_home: Button? = null
    var inputEmail: EditText ?= null
    var inputPassword: EditText ?= null
    var buttonLogin: Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // go to register
        btn_register = findViewById(R.id.btn_register)
        btn_register?.setOnClickListener{ goToRegister() }
        //go to home
        btn_home = findViewById(R.id.btn_home)
        btn_home?.setOnClickListener{ goToMainPage() }
        //for login
        inputEmail = findViewById(R.id.input_user)
        inputPassword = findViewById(R.id.input_pass)
        buttonLogin = findViewById(R.id.btn_login)
        buttonLogin?.setOnClickListener{ login()}
    }
    //capturando datos que el usuario ingresa
    private fun login(){
        val email = inputEmail?.text.toString()
        val password = inputPassword?.text.toString()

        Toast.makeText(this, "El email es: ${email}", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "La contraseña es: ${password}", Toast.LENGTH_LONG).show()
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


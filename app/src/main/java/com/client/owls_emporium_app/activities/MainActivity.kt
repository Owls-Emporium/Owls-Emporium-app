package com.client.owls_emporium_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.network.models.ResponseHttp
import com.client.owls_emporium_app.network.providers.UsersProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var btn_register: Button? = null
    var btn_home: Button? = null
    var inputEmail: EditText ?= null
    var inputPassword: EditText ?= null
    var buttonLogin: Button ?= null
    var usersProvider = UsersProvider()

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

        if (isValidForm(email,password)){
            usersProvider.login(email,password)?.enqueue(object: Callback<ResponseHttp>{
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {
                    Log.d("MainActivity", "Response: ${response.body()}")

                    if(response.body()?.isSuccess == true){
                        Toast.makeText(this@MainActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this@MainActivity, "contraseña o usuario incorrecto", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d("MainActivity","Hubo un error ${t.message}")
                    Toast.makeText(this@MainActivity, " ${t.message}", Toast.LENGTH_LONG).show()
                }

            } )
        }
        else{
            Toast.makeText(this, "No es valido", Toast.LENGTH_LONG).show()
        }

    }
    //validar que sea un correo @gmail.com
    fun String.isEmailValid(): Boolean{
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
    //validando datos
    private fun isValidForm(email: String, password: String): Boolean{
        if (email.isBlank()){
            Toast.makeText(this,"Ingresa el correo",Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isBlank()){
            Toast.makeText(this,"Ingresa la contraseña",Toast.LENGTH_SHORT).show()
            return false
        }
        /*
        if(email.isEmailValid()){
            Toast.makeText(this,"EL correo ingresado no es valido, verificar",Toast.LENGTH_SHORT).show()
            return false
        }*/
        return true
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


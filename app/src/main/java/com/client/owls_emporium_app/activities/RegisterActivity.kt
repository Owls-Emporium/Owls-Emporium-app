package com.client.owls_emporium_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.network.models.ResponseHttp
import com.client.owls_emporium_app.network.models.User
import com.client.owls_emporium_app.network.providers.UsersProvider
import com.client.owls_emporium_app.network.utils.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    val TAG = "RegisterActivity"
    var btn_login: Button? = null
    var inputName: EditText?= null
    var inputLastname: EditText?= null
    var inputEmail: EditText?=null
    var inputPhone: EditText?= null
    var inputPass: EditText?=null
    var inputConfirmPass: EditText?= null
    var registerButton: Button?= null

    var usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_login = findViewById(R.id.btn_login)
        inputName = findViewById(R.id.input_name)
        inputLastname = findViewById(R.id.input_lastname)
        inputEmail = findViewById(R.id.input_email)
        inputPhone = findViewById(R.id.input_phone)
        inputPass = findViewById(R.id.input_pass)
        inputConfirmPass = findViewById(R.id.input_confirm_pass)
        registerButton = findViewById(R.id.register_button)


        btn_login?.setOnClickListener{ goToLogin() }
        registerButton?.setOnClickListener{register()}
    }

    private fun register(){
        val name = inputName?.text.toString()
        val lastname = inputLastname?.text.toString()
        val email = inputEmail?.text.toString()
        val phone = inputPhone?.text.toString()
        val password = inputPass?.text.toString()
        val confirmPassword = inputConfirmPass?.text.toString()

        if (isValidForm(name = name,lastname = lastname, email= email, phone= phone, password=password, confirmPassword=confirmPassword )){

            val user = User(
                name = name,
                lastname = lastname,
                email = email,
                phone = phone,
                password = password
            )
            usersProvider.register(user)?.enqueue(object: Callback<ResponseHttp>{
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {
                    if (response.body()?.isSuccess == true){
                        saveUserInSession(response.body()?.data.toString())
                        goToMainPage()
                    }
                    Toast.makeText(this@RegisterActivity,response.body()?.message,Toast.LENGTH_LONG).show()
                    Log.d(TAG,"Response: ${response}")
                    Log.d(TAG,"Body: ${response.body()}")
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG,"Se produjo un error ${t.message}")
                    Toast.makeText(this@RegisterActivity,"Se produjo un error ${t.message}",Toast.LENGTH_LONG).show()
                }
            })
        }
    }
    private fun goToMainPage() {
        val i = Intent(this, MainPageActivity::class.java)
        startActivity(i)
    }
    //para mantener la sesion del usuario(almacena datos en sesion)
    private fun saveUserInSession(data: String){
        val sharePref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data,User::class.java)
        sharePref.save("user",user)
    }
    private fun isValidForm(
        name: String,
        lastname: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ): Boolean{
        if (name.isBlank()){
            Toast.makeText(this,"Debes ingresar el nombre",Toast.LENGTH_SHORT).show()
            return false
        }
        if (lastname.isBlank()){
            Toast.makeText(this,"Debes ingresar el apellido",Toast.LENGTH_SHORT).show()
            return false
        }
        if (email.isBlank()){
            Toast.makeText(this,"Debes ingresar un correo",Toast.LENGTH_SHORT).show()
            return false
        }
        if (phone.isBlank()){
            Toast.makeText(this,"Debes ingresar el numero de telefono",Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isBlank()){
            Toast.makeText(this,"Debes ingresar una contraseña",Toast.LENGTH_SHORT).show()
            return false
        }
        if (confirmPassword.isBlank()){
            Toast.makeText(this,"Debes confirmar la contraseña, las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            return false
        }
        /*
        if(!email.isEmailValid()){
            Toast.makeText(this,"El email no es valido",Toast.LENGTH_SHORT).show()
            return false
        }*/

        if (password != confirmPassword){
            Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}

//si te niega conectarte la peticion de creear usuario
//    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
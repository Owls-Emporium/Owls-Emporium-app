package com.client.owls_emporium_app.activities

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.network.models.ResponseHttp
import com.client.owls_emporium_app.network.models.User
import com.client.owls_emporium_app.network.providers.UsersProvider
import com.client.owls_emporium_app.network.utils.SharedPref
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateProfileActivity : AppCompatActivity() {

    val TAG = "ClientUpdateActivity"
    var circleImageUser : CircleImageView? = null
    var editTextName: EditText? = null
    var editTextLastName: EditText? = null
    var editTextPhone: EditText? = null
    var btn_update: Button? = null

    var sharedPref: SharedPref? = null
    var user: User?= null

    private var imageFile : File?= null
    var usersProvider: UsersProvider? = null

    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        sharedPref = SharedPref(this)

        toolbar = findViewById(R.id.toolbar)
        toolbar?.title = "Atras"
        toolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        circleImageUser = findViewById(R.id.circleimage_user)
        editTextName = findViewById(R.id.input_name)
        editTextLastName = findViewById(R.id.input_lastname)
        editTextPhone = findViewById(R.id.input_phone)
        btn_update = findViewById(R.id.btn_update)

        getUserFromSession()

        usersProvider = UsersProvider(user?.sessionToken)

        editTextName?.setText(user?.name)
        editTextLastName?.setText(user?.lastname)
        editTextPhone?.setText(user?.phone)

        if (!user?.image.isNullOrBlank()) {
            Glide.with(this).load(user?.image).into(circleImageUser!!)
        }

        circleImageUser?.setOnClickListener{ selectImage() }
        btn_update?.setOnClickListener { updateData() }
    }

    private fun updateData() {

        val name = editTextName?.text.toString()
        val lastname = editTextLastName?.text.toString()
        val phone = editTextPhone?.text.toString()

        user?.name = name
        user?.lastname = lastname
        user?.phone = phone

        if (imageFile != null) {
            usersProvider?.update(imageFile!!, user!!)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")

                    Toast.makeText(this@UpdateProfileActivity, response.body()?.message, Toast.LENGTH_SHORT).show()

                    if (response.body()?.isSuccess == true) {
                        saveUserInSession(response.body()?.data.toString())
                    }

                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    Toast.makeText(this@UpdateProfileActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }

            })
        }
        else {
            usersProvider?.updateWithoutImage(user!!)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")

                    Toast.makeText(this@UpdateProfileActivity, response.body()?.message, Toast.LENGTH_SHORT).show()

                    if (response.body()?.isSuccess == true) {
                        saveUserInSession(response.body()?.data.toString())
                    }

                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    Toast.makeText(this@UpdateProfileActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }

            })
        }

    }

    private fun saveUserInSession(data: String) {
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref?.save("user", user)
    }

    private fun getUserFromSession() {
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            //si exite el usuario en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }

    private val startImageForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->

        val resultCode = result.resultCode
        val data = result.data

        if(resultCode == Activity.RESULT_OK){
            val fileUri = data?.data
            imageFile = File(fileUri?.path) //el archivo que vamos a guradar como imagen en el server
            circleImageUser?.setImageURI(fileUri)
        }
        else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this,ImagePicker.getError(data),Toast.LENGTH_LONG).show()
        }
        //si no es ok
        else{
            Toast.makeText(this,"tarea se cancelo, error",Toast.LENGTH_LONG).show()
        }
    }

    private fun selectImage(){
        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1080,1080)
            .createIntent { intent ->
                startImageForResult.launch(intent)
            }
    }

}
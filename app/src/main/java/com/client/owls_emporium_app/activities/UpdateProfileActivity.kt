package com.client.owls_emporium_app.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.client.owls_emporium_app.R
import com.github.dhaval2404.imagepicker.ImagePicker
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class UpdateProfileActivity : AppCompatActivity() {

    var circleImageUser : CircleImageView?= null
    var btn_update : Button? = null


    private var imageFile : File?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        circleImageUser = findViewById(R.id.circleimage_user)
        btn_update = findViewById(R.id.btn_update)

        circleImageUser?.setOnClickListener{ selectImage() }
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
package com.client.owls_emporium_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.client.owls_emporium_app.R
import de.hdodenhof.circleimageview.CircleImageView

class UpdateProfileActivity : AppCompatActivity() {

    var circleImageUser : CircleImageView?= null
    var btn_update : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

    }
}
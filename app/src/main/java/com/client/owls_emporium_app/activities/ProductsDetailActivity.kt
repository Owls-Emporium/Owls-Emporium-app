package com.client.owls_emporium_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.network.models.Product
import com.client.owls_emporium_app.network.utils.SharedPref
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.gson.Gson

class ProductsDetailActivity : AppCompatActivity() {

    val TAG = "ProductsDetail"
    var product: Product? = null
    val gson = Gson()

    var imageSlider: ImageSlider? = null
    var textViewName: TextView? = null
    var textViewDescription: TextView? = null
    var textViewPrice: TextView? = null


    var sharedPref: SharedPref? = null
    var selectedProducts = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_detail)

        product = gson.fromJson(intent.getStringExtra("product"), Product::class.java)
        sharedPref = SharedPref(this)

        imageSlider = findViewById(R.id.imageslider)
        textViewName = findViewById(R.id.textview_name)
        textViewDescription = findViewById(R.id.textview_description)
        textViewPrice = findViewById(R.id.textview_price)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(product?.image1, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(product?.image2, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(product?.image3, ScaleTypes.CENTER_CROP))

        imageSlider?.setImageList(imageList)

        textViewName?.text = product?.name
        textViewDescription?.text = product?.description
        textViewPrice?.text = "$ ${product?.price}"


    }


}
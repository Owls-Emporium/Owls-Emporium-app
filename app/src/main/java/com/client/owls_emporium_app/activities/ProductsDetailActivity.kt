package com.client.owls_emporium_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.network.models.Product
import com.client.owls_emporium_app.network.utils.SharedPref
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.hdodenhof.circleimageview.CircleImageView

class ProductsDetailActivity : AppCompatActivity() {

    val TAG = "ProductsDetail"
    var product: Product? = null
    val gson = Gson()
    //creando variables para almacer el valor de respuesta
    var imageSlider: ImageSlider? = null
    var textViewName: TextView? = null
    var textViewDescription: TextView? = null
    var textViewPrice: TextView? = null
    var textViewCategory: TextView? = null
    var textViewStatus: TextView? = null
    var textViewStatement: TextView? = null
    var textViewUserName: TextView? = null
    var circleImagUser: ImageSlider? = null
    var phoneUser: TextView? = null
    var buttonAdd: Button? = null
    var sharedPref: SharedPref? = null
    var selectedProducts = ArrayList<Product>()

    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_detail)

        product = gson.fromJson(intent.getStringExtra("product"), Product::class.java)
        sharedPref = SharedPref(this)
        //find cada textview
        imageSlider = findViewById(R.id.imageslider)
        textViewName = findViewById(R.id.textview_name)
        textViewDescription = findViewById(R.id.textview_description)
        textViewPrice = findViewById(R.id.textview_price)
        textViewCategory = findViewById(R.id.textview_category)
        textViewStatus = findViewById(R.id.textview_status)
        textViewStatement = findViewById(R.id.textview_statement)
        textViewUserName = findViewById(R.id.textview_username)
//        circleImagUser = findViewById(R.id.circleimg_user)

        buttonAdd = findViewById(R.id.btn_add_product)
        //arreglo de las imagenes para que se muestren
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(product?.image1, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(product?.image2, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(product?.image3, ScaleTypes.CENTER_CROP))

        imageSlider?.setImageList(imageList)

        val imageUser = ArrayList<SlideModel>()
        imageUser.add(SlideModel(product?.imgUser, ScaleTypes.CENTER_CROP))

        circleImagUser?.setImageList(imageUser)

        toolbar = findViewById(R.id.toolbar)
        toolbar?.title = "Atras"
        toolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        textViewName?.text = product?.name
        textViewDescription?.text = product?.description
        textViewPrice?.text = "$ ${product?.price}"
        textViewCategory?.text = product?.idCategory
        textViewStatus?.text = "${product?.idStatus}"
        textViewUserName?.text = product?.idUser
        textViewStatement?.text = product?.idStatement

        buttonAdd?.setOnClickListener { addToBag() }

        getProductsFromSharedPref()

    }

    private fun addToBag() {
        val index = getIndexOf(product?.id!!) // INDICE DEL PRODUCTO SI ES QUE EXISTE EN SHARED PREF
        if (index == -1) { // ESTE PRODUCTO NO EXISTE AUN EN SHARED PREF
            if (product?.quantity == null) {
            }
            selectedProducts.add(product!!)
        }

        sharedPref?.save("order", selectedProducts)
        Toast.makeText(this, "Producto añadido a favoritos, puedes verlos en tu perfil", Toast.LENGTH_LONG).show()

    }

    private fun getIndexOf(idProduct: String): Int {
        var pos = 0

        for (p in selectedProducts) {
            if (p.id == idProduct) {
                return pos
            }
            pos++
        }

        return -1
    }


    private fun getProductsFromSharedPref() {

        if (!sharedPref?.getData("order").isNullOrBlank()) { // EXISTE UNA ORDEN EN SHARED PREF
            val type = object: TypeToken<ArrayList<Product>>() {}.type
            selectedProducts = gson.fromJson(sharedPref?.getData("order"), type)
            val index = getIndexOf(product?.id!!)


            for (p in selectedProducts) {
                Log.d(TAG, "Shared pref: $p")
            }
        }

    }


}
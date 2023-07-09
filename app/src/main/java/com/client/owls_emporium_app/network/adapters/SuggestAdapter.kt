package com.client.owls_emporium_app.network.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.activities.ProductsDetailActivity
import com.client.owls_emporium_app.network.models.Suggest
import com.client.owls_emporium_app.network.utils.SharedPref

class SuggestAdapter(val context: Activity, val suggest : ArrayList<Suggest>): RecyclerView.Adapter<SuggestAdapter.SuggestViewHolder>() {

    val sharedPref = SharedPref(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_product, parent, false)
        return SuggestViewHolder(view)

    }

    override fun getItemCount(): Int {
        return suggest.size
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        val product = suggest[position] // Cada una de los productos

        holder.textViewName.text = product.name
        holder.textViewPrice.text = "$ ${product.price}"
        Glide.with(context).load(product.image1).into(holder.imageViewProduct)

        holder.itemView.setOnClickListener { goToDetail(product) }
    }

    private fun goToDetail(product: Suggest) {

        val i = Intent(context, ProductsDetailActivity::class.java)
        i.putExtra("product", product.toJson())
        context.startActivity(i)
    }

    class SuggestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewPrice: TextView
        val imageViewProduct: ImageView

        init {
            textViewName = view.findViewById(R.id.tv_name_product)
            textViewPrice = view.findViewById(R.id.tv_price_product)
            imageViewProduct = view.findViewById(R.id.imageview_product_category)
        }

    }
}
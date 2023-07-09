package com.client.owls_emporium_app.network.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.activities.ProductsDetailActivity
import com.client.owls_emporium_app.activities.SaveProductsActivity
import com.client.owls_emporium_app.network.models.Product
import com.client.owls_emporium_app.network.utils.SharedPref

class SaveProductsAdapter(val context: FragmentActivity, val products: ArrayList<Product>): RecyclerView.Adapter<SaveProductsAdapter. SaveProductsViewHolder>(){

    val sharedPref = SharedPref(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  SaveProductsViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_post_created, parent, false)
        return  SaveProductsViewHolder(view)

    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder:  SaveProductsViewHolder, position: Int) {
        val product = products[position] // Cada una de los productos

        holder.textViewName.text = product.name
        holder.textViewPrice.text = "$ ${product.price}"
        Glide.with(context).load(product.image1).into(holder.imageViewProduct)

        holder.imageViewDelete.setOnClickListener { deleteItem(position) }

        holder.itemView.setOnClickListener {goToDetail(product)}
    }

    private fun deleteItem(position: Int) {
        products.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, products.size)
        sharedPref.save("order", products)
    }

    private fun goToDetail(product: Product){

        val i = Intent(context, ProductsDetailActivity::class.java)
        i.putExtra("product", product.toJson())
        context.startActivity(i)
    }

    class SaveProductsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewPrice: TextView
        val imageViewProduct: ImageView
        val imageViewDelete: ImageView

        init {
            textViewName = view.findViewById(R.id.tv_name_product_save)
            textViewPrice = view.findViewById(R.id.tv_price_product_save)
            imageViewProduct = view.findViewById(R.id.imageview_product_save)
            imageViewDelete= view.findViewById(R.id.imageview_delete)

        }
    }

}


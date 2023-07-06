package com.client.owls_emporium_app.network.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.client.owls_emporium_app.R
import com.client.owls_emporium_app.network.models.Category
import com.client.owls_emporium_app.network.utils.SharedPref

class CategoriesAdapter(val context: Activity, val categories: ArrayList<Category>): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>(){

    val sharedPref = SharedPref(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_category, parent, false)
        return CategoriesViewHolder(view)

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position] // Cada una de las categorias

        holder.textViewCategory.text = category.name
        Glide.with(context).load(category.image).into(holder.imageViewCategory)
    }

    class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewCategory: TextView
        val imageViewCategory: ImageView

        init {
            textViewCategory = view.findViewById(R.id.textview_category)
            imageViewCategory = view.findViewById(R.id.imageview_category)
        }
    }

}

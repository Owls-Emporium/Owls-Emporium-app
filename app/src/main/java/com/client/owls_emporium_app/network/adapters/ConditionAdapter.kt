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
import com.client.owls_emporium_app.network.models.Condition
import com.client.owls_emporium_app.network.utils.SharedPref

class ConditionAdapter(val context: Activity, val condition: ArrayList<Condition>): RecyclerView.Adapter<ConditionAdapter.ConditionViewHolder>(){

    val sharedPref = SharedPref(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConditionViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_condition, parent, false)
        return ConditionViewHolder(view)

    }

    override fun getItemCount(): Int {
        return condition.size
    }

    override fun onBindViewHolder(holder: ConditionViewHolder, position: Int) {
        val condition = condition[position] // Cada una de las categorias

        holder.textViewCondition.text = condition.name
       Glide.with(context).load(condition.image).into(holder.imageViewCondition)
    }

    class ConditionViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewCondition: TextView
        val imageViewCondition: ImageView

        init {
            textViewCondition = view.findViewById(R.id.textview_condition)
            imageViewCondition = view.findViewById(R.id.imageview_condition)
        }
    }

}

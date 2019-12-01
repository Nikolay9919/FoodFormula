package com.example.foodformula.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodformula.R


class StringAdapter(var array: List<String?>) :
    RecyclerView.Adapter<StringAdapter.ViewHolder>() {


    override fun getItemCount(): Int = array.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent!!.context).inflate(
                R.layout.string_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = array[position]!!

    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvName = v.findViewById(R.id.recipe) as TextView
    }


}
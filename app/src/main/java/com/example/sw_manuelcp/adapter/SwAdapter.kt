package com.example.sw_manuelcp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sw_manuelcp.R

class SwAdapter(var SwPersonajes:List<com.example.sw_manuelcp.ModeloDatos.Result>):RecyclerView.Adapter<SwViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SwViewHolder(layoutInflater.inflate(R.layout.item_personaje,parent,false))
    }

    override fun onBindViewHolder(holder: SwViewHolder, position: Int) {
        val item = SwPersonajes[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = SwPersonajes.size // tamanio de la lista
}
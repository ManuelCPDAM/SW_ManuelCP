package com.example.sw_manuelcp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sw_manuelcp.databinding.ItemPersonajeBinding

class SwViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemPersonajeBinding.bind(view)

    fun render(SwModel: com.example.sw_manuelcp.ModeloDatos.Result){
        // cambiamos el text view del nombre al modelo del nombre
        binding.TvNombre.text = SwModel.name

        // creamos una variable para guardar el numero de pelis en las que ha participado cada actor
        var numPelis = SwModel.films.count() // Usamos elcount para saber el numero de la lista de peliculas
        binding.TvNumPelisMostrar.text = numPelis.toString() // Y lo pasamos a string en el textview del numero de pelis

        binding.TvAlturaMostrar.text = SwModel.height // Y por ultimo hacemos lo mismo con la altura, que de por si ya es un string
    }
}
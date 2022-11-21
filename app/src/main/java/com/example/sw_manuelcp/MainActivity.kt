package com.example.sw_manuelcp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sw_manuelcp.ModeloDatos.StarWarResponse
import com.example.sw_manuelcp.adapter.SwAdapter
import com.example.sw_manuelcp.adapter.SwViewHolder
import com.example.sw_manuelcp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    // Variables que usaremos durante el programa
    lateinit var binding:ActivityMainBinding
    lateinit var adapter: SwAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        // Activamos el binding
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        binding.BtVerListado.setOnClickListener { // Si pulsan el boton de ver listado
            MostrarRecycler() // Llamamos al metodo para mostrar el recycler
        }


        binding.BtGuardarListado.setOnClickListener { // Si pulsan el boton de guardar listado
            val miDialogo = MiDialogFragment()
            miDialogo.show(supportFragmentManager,"MiDialogo")
        }

    }

    // FUNCION QUE MUESTRA EL RECYLER
    private fun MostrarRecycler() {
        // estilo de recycler
        binding.Recycler.layoutManager = LinearLayoutManager(this)
        adapter = SwAdapter(emptyList())
        binding.Recycler.adapter = adapter

        // Usamos la corrutina
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<StarWarResponse> =
                getRetrofit().create(APIService::class.java).getStarWar("people/") // parte mutable de la url
            val personaje = call.body() // hacmeos la llamada

            // en otro hilo
            runOnUiThread {
                if (call.isSuccessful) {
                    // Si la llamada tiene exito
                    val resultado = personaje?.results ?: emptyList()
                    adapter.SwPersonajes = resultado // cambiamos el adapter al resultado obtenido
                    adapter.notifyDataSetChanged()
                } else {
                    // Sino mostramos error
                    mostrarError()
                }
            }
        }
    }


    // Función por la que pasamos la base url de la api
    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    // Función que muestra un toast con mensaje de error
    private fun mostrarError(){
        Toast
            .makeText(this@MainActivity, "Ha ocurrido un error", Toast.LENGTH_SHORT)
            .show()
    }
}
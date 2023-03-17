package com.example.proyectofinal_pescaenaguadulce


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Esta clase es un adaptador personalizado para la lista de peces capturados
 * que se muestra en la pantalla "Mis Peces".
 * Extiende la clase ArrayAdapter y se utiliza para crear vistas personalizadas
 * para cada elemento de la lista.
 *
 * @param context El contexto de la aplicación.
 * @param viewToPaint La vista a pintar.
 * @param pezList Una lista de objetos PezCapturado que se muestran en la lista.
 * @author [Samuel Fernández Pérez]
 */
class MisPecesArrayAdapter (context : Context, viewToPaint : Int, private val pezList: ArrayList<PezCapturado>) : ArrayAdapter<PezCapturado>(context, viewToPaint, pezList){
    /**
     * Esta función es llamada para obtener una vista personalizada para cada elemento de la lista.
     *
     * @param position La posición del elemento en la lista.
     * @param convertView La vista reutilizada para mostrar el elemento.
     * @param parent El ViewGroup al que pertenece a la vista.
     * @return La vista personalizada para el elemento en la posición dada.
     */
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.i("Demostración: ", "Entrando en el getView" )
        val inflater = LayoutInflater.from(context)
        val currentListItem = inflater.inflate(R.layout.item_list_mis_peces, null)
        val clasePez = currentListItem.findViewById<TextView>(R.id.textClase)
        val pesoPez = currentListItem.findViewById<TextView>(R.id.textPeso)
        clasePez.text = pezList.get(position).clase
        pesoPez.text = pezList.get(position).peso.toString()
        return currentListItem
    }
}


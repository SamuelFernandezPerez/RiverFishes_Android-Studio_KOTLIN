package com.example.proyectofinal_pescaenaguadulce

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * Esta clase es un adaptador personalizado para la lista de peces existentes
 * que se muestra en la pantalla principal.
 * Extiende la clase ArrayAdapter y se utiliza para crear vistas personalizadas
 * para cada elemento de la lista.
 *
 * @param context El contexto de la aplicación.
 * @param viewToPaint La vista a pintar.
 * @param pezList Una lista de objetos PezExistente que se muestran en la lista.
 * @author [Samuel Fernández pérez]
 */
class PecesExistentesArrayAdapter (context : Context, viewToPaint : Int, private val pezList: ArrayList<PezExistente>) : ArrayAdapter<PezExistente> (context, viewToPaint, pezList){
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
        val inflater = LayoutInflater.from(context)
        val currentListItem = inflater.inflate(R.layout.item_list_peces_existentes, null)
        val tipoPez = currentListItem.findViewById<TextView>(R.id.textClase)
        val zonaPesca = currentListItem.findViewById<TextView>(R.id.textZona)
        val imagePez = currentListItem.findViewById<ImageView>(R.id.imagePez)
        tipoPez.text = pezList.get(position).clase
        zonaPesca.text = pezList.get(position).zona
        imagePez.setImageResource(pezList.get(position).imagePez)
        return currentListItem
    }
}
package com.example.proyectofinal_pescaenaguadulce

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * Esta clase representa un adaptador para mostrar una lista de zonas de pesca en una vista ListView.
 *
 * @property context El contexto en el que se está utilizando el adaptador.
 * @property viewToPaint La vista a pintar.
 * @property zonaList La lista de zonas de pesca a mostrar.
 * @constructor Crea un nuevo objeto ZonaArrayAdapter con el contexto, la vista y la lista de zonas de pesca dadas.
 * @extends ArrayAdapter <Zona> Clase que permite mostrar una lista de objetos Zona en una vista ListView.
 * @return Una instancia de la clase ZonaArrayAdapter.
 * @versión 1.0
 * @author [Samuel Fernández Pérez]
 */
class ZonaArrayAdapter (context : Context, viewToPaint : Int, private val zonaList: ArrayList<Zona>) : ArrayAdapter<Zona> (context, viewToPaint, zonaList){
    /**
     * Esta función  se encarga de crear y devolver la vista que representa un elemento de la lista.
     *
     * @param position La posición del elemento en la lista.
     * @param convertView La vista anterior que se puede reutilizar para la nueva vista.
     * @param parent El ViewGroup al que pertenece a la vista.
     * @return La vista que representa el elemento de la lista en la posición dada.
     */
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.i("Demostración: ", "Entrando en el getView" )
        val inflater = LayoutInflater.from(context)
        val currentListItem = inflater.inflate(R.layout.item_list_zonas_de_pesca, null)
        val nombreZona = currentListItem.findViewById<TextView>(R.id.textNombreZona)
        val lugar = currentListItem.findViewById<TextView>(R.id.textLugarZona)
        val imageZona = currentListItem.findViewById<ImageView>(R.id.imageZona)
        val imageMap = currentListItem.findViewById<ImageView>(R.id.imageMap)
        nombreZona.text = zonaList.get(position).nombre
        lugar.text = zonaList.get(position).lugar
        imageZona.setImageResource(zonaList.get(position).imageZona)
        imageMap.setImageResource(zonaList.get(position).imageMap)
        return currentListItem
    }
}
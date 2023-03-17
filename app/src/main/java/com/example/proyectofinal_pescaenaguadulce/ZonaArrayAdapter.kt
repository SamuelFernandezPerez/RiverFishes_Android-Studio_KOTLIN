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

class ZonaArrayAdapter (context : Context, viewToPaint : Int, private val zonaList: ArrayList<Zona>) : ArrayAdapter<Zona> (context, viewToPaint, zonaList){
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
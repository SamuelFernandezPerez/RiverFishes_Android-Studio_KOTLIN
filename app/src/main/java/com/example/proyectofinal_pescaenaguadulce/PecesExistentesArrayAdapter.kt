package com.example.proyectofinal_pescaenaguadulce

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class PecesExistentesArrayAdapter (context : Context, viewToPaint : Int, private val pezList: ArrayList<PezExistente>) : ArrayAdapter<PezExistente> (context, viewToPaint, pezList){
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
package com.example.proyectofinal_pescaenaguadulce


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MisPecesArrayAdapter (context : Context, viewToPaint : Int, private val pezList: ArrayList<PezCapturado>) : ArrayAdapter<PezCapturado>(context, viewToPaint, pezList){
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


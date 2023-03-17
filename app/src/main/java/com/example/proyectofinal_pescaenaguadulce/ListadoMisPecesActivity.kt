package com.example.proyectofinal_pescaenaguadulce

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Clase para mostrar un listado de peces capturados por un usuario.
 * Esta clase extiende de la clase AppCompatActivity y utiliza Firebase
 *
 *para obtener la lista de peces capturados por el usuario actual.
 * También permite agregar nuevos peces capturados y editar o eliminar los existentes.
 *
 * @constructor Crea una instancia de la clase ListadoMisPecesActivity.
 * @property pecesCapturados La lista de peces capturados por el usuario actual.
 * @author [Samuel Fernández Pérez]
 */
class ListadoMisPecesActivity : AppCompatActivity() {
    val pecesCapturados = arrayListOf<PezCapturado>()
    /**
     * Función que se llama al crear la actividad.
     * Se encarga de obtener la colección de peces capturados desde Firebase
     * y configurar el ListView para mostrarlos.
     *
     * @param SavedInstanceState El estado de la instancia de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_mis_peces)
        getPezCollection()
        val imagePez = findViewById<ImageView>(R.id.imagePez)

        imagePez.setOnClickListener{
            val i = Intent(applicationContext, AgregarPezCapturadoActivity::class.java)
            startActivity(i)
        }
    }
    /**
     * Obtiene la lista de peces capturados de la base de datos
     * y completa la vista de lista con los datos.
     */
    @SuppressLint("CutPasteId")
    fun getPezCollection(){
        val listViewPecesCapturados = findViewById<ListView>(R.id.ListViewMisPeces)
        val db = Firebase.firestore
        val currentuseremail = FirebaseAuth.getInstance().currentUser?.email.toString()
        db.collection("pecesCapturados")
            .whereEqualTo("email_usuario",currentuseremail)
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    val currentclase = document.data["clase"].toString()
                    val currentpeso = document.data["peso"].toString().toFloat()
                    val currentcebo = document.data["cebo"].toString()
                    val currentgenero = PezCapturado.Genero.valueOf(document.data["genero"].toString())
                    val currentzona = document.data["zona"].toString()
                    val currentlugar = document.data["lugar"].toString()
                    val currentclima = document.data["clima"].toString()
                    val currentid = document.id
                    val currentpez = PezCapturado(currentclase, currentzona, currentpeso, currentgenero, currentlugar, currentclima, currentcebo, currentid)
                    pecesCapturados.add(currentpez)
                }
                when(pecesCapturados.size){
                    0 -> listViewPecesCapturados.emptyView = findViewById<TextView>(R.id.textSinPeces)
                    else -> {
                        val listViewMisPeces = findViewById<ListView>(R.id.ListViewMisPeces)
                        val pezArrayAdapter = MisPecesArrayAdapter(applicationContext, R.layout.item_list_mis_peces, pecesCapturados)
                        listViewMisPeces.adapter = pezArrayAdapter
                        listViewMisPeces.setOnItemClickListener{parent, view, position, id ->
                            val pezActual = pecesCapturados.get(position)
                            val i = Intent(applicationContext, EdicionMisPecesActivity::class.java)
                            i.putExtra("pez", pezActual)
                            startActivity(i)
                        }
                    }
                }
            }
            .addOnFailureListener{ exception ->
                val listViewMisPeces = findViewById<ListView>(R.id.ListViewMisPeces)
                listViewMisPeces.emptyView = findViewById<TextView>(R.id.textSinPeces)
            }
    }
    /**
     * Esta función anula el comportamiento predeterminado del botón Atrás cuando se presiona.
     * De forma predeterminada, el botón Atrás cierra la actividad actual
     * y vuelve a la anterior en la pila de actividades.
     */
    override fun onBackPressed() {
    }
}
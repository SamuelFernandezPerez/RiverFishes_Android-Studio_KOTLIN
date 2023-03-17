package com.example.proyectofinal_pescaenaguadulce

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * Clase que representa la actividad principal de la aplicación. Esta actividad
 * contiene la vista del menú principal de la aplicación, que permite al usuario
 * navegar a otras partes de la aplicación.
 * Hereda de la clase [AppCompatActivity] de la librería de soporte de Android.
 *
 * @author [Samuel Fernandez Pérez]
 * @constructor Crea una instancia de la actividad principal del menú.
 * */
class MenuActivity : AppCompatActivity() {
    /**
     * Sobrescribe el comportamiento predeterminado de la creación de la actividad, establece
     * el diseño, inicializa los elementos de la interfaz de usuario y configura sus escuchas
     * de clics para iniciar otras actividades en respuesta a los eventos de clics.
     *
     * @param SavedInstanceState El estado de instancia guardado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val pecesExistentesContainer = findViewById<RelativeLayout>(R.id.pecesExistentesContainer)
        val zonasContainer = findViewById<RelativeLayout>(R.id.zonasContainer)
        val misPecesContainer = findViewById<RelativeLayout>(R.id.misPecesContainer)
        val textListadoZonasPesca = findViewById<TextView>(R.id.textListadoZonasPesca)
        val textListadoMisPeces = findViewById<TextView>(R.id.textListadoMisPeces)
        val textListadoPecesExistentes = findViewById<TextView>(R.id.textListadoPecesExistentes)
        val font = Typeface.createFromAsset(assets, "fonts/coco.ttf")
        textListadoZonasPesca.typeface = font
        textListadoMisPeces.typeface = font
        textListadoPecesExistentes.typeface = font
        pecesExistentesContainer.setOnClickListener(){
            val i = Intent(applicationContext, ListadoPecesExistentesActivity::class.java)
            startActivity(i)
        }
        zonasContainer.setOnClickListener(){
            val i = Intent(applicationContext, ListadoZonasPescaActivity::class.java)
            startActivity(i)
        }
        misPecesContainer.setOnClickListener(){
            val i = Intent(applicationContext, ListadoMisPecesActivity::class.java)
            startActivity(i)
        }
    }
    /**
     * Anula el comportamiento predeterminado del menú de opciones para inflar el diseño del menú.
     *
     * @param menu El menú a inflar.
     * @return Un valor booleano que indica si el menú se infló correctamente.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    /**
     * Anula el comportamiento predeterminado del menú de opciones para manejar
     * la selección de elementos específicamente para cerrar la sesión del usuario
     * y redirigirlo a la actividad de acceso.
     *
     * @param item El elemento de menú seleccionado.
     * @return Un valor booleano que indica si se manejó el elemento de menú seleccionado.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.itemLogOut -> {
                FirebaseAuth.getInstance().signOut()
                val i = Intent(applicationContext, AccesoActivity::class.java)
                startActivity(i)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    /**
     * Anula el comportamiento predeterminado del botón Atrás para mostrar un mensaje Toas
     * indicando que el usuario necesita cerrar sesión para salir de la aplicación.
     *
     * @return void
     */
    override fun onBackPressed() {
        val toastAlert = Toast.makeText(applicationContext, "Debes cerrar sesión para salir", Toast.LENGTH_LONG
        )
        toastAlert.show()
    }
}
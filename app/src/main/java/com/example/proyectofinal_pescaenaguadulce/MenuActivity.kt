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

class MenuActivity : AppCompatActivity() {
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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
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
    override fun onBackPressed() {
        val toastAlert = Toast.makeText(applicationContext, "Debes cerrar sesión para salir", Toast.LENGTH_LONG
        )
        toastAlert.show()
    }
}
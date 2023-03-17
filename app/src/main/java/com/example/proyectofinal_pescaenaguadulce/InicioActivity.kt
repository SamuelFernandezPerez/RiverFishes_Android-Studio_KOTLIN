package com.example.proyectofinal_pescaenaguadulce

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

/**
 * Clase que representa la pantalla de inicio de la aplicación.
 * Hereda de la clase [AppCompatActivity] de la librería de soporte de Android.
 *
 * @Autor [Samuel Fernández Pérez]
 */
class InicioActivity : AppCompatActivity() {
    /**
     * Función que se llama cuando se crea la actividad.
     * @param objeto savedInstanceState [Bundle] que contiene el estado anterior de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val textTitulo = findViewById<TextView>(R.id.textTitulo)
        val buttonEntrar = findViewById<Button>(R.id.buttonEntrar)
        val font = Typeface.createFromAsset(assets, "fonts/fisherman.ttf")
        textTitulo.typeface = font
        buttonEntrar.typeface = font
        buttonEntrar.setOnClickListener {
            val i = Intent(applicationContext, AccesoActivity::class.java)
            startActivity(i)
        }
    }

}
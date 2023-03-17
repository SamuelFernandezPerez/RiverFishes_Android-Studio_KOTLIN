package com.example.proyectofinal_pescaenaguadulce

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class InicioActivity : AppCompatActivity() {
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
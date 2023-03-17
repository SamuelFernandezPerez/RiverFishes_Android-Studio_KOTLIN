package com.example.proyectofinal_pescaenaguadulce

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AccesoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acceso)
        val botonRegistrar = findViewById<Button>(R.id.buttonRegistrar)
        val botonAcceder = findViewById<Button>(R.id.buttonAcceder)
        val font = Typeface.createFromAsset(assets, "fonts/fisherman.ttf")
        botonRegistrar.typeface = font
        botonAcceder.typeface = font
        botonRegistrar.setOnClickListener(){
            registrar()
        }
        botonAcceder.setOnClickListener(){
            acceder()
        }
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(Intent(applicationContext,MenuActivity::class.java))
        }
    }
    fun registrar(){
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextContrasena = findViewById<EditText>(R.id.editTextContraseña)
        if (editTextEmail.text.isNotEmpty() && editTextContrasena.text.isNotEmpty()){
            if(editTextContrasena.length() < 6 || editTextEmail.length() < 6){
                val toastAlert = Toast.makeText(applicationContext, "Longitud minima de campo: 6", Toast.LENGTH_LONG)
                toastAlert.show()
            }
            if(!editTextEmail.text.toString().contains("@")){
                val toastAlert = Toast.makeText(applicationContext, " El email debe contener @", Toast.LENGTH_LONG)
                toastAlert.show()
            }
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(editTextEmail.text.toString(), editTextContrasena.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        val toastAlert = Toast.makeText(applicationContext, "¡Usuario creado con exito!", Toast.LENGTH_LONG)
                        toastAlert.show()
                    }else{
                        showAlertRegister()
                    }
                }
        }else{
            val toastAlert = Toast.makeText(applicationContext, "Error. Algún campo está vacío", Toast.LENGTH_LONG)
            toastAlert.show()
        }
    }
    fun acceder(){
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextContrasena = findViewById<EditText>(R.id.editTextContraseña)
        if (editTextEmail.text.isNotEmpty() && editTextContrasena.text.isNotEmpty()){
            if(editTextContrasena.length() < 6 || editTextEmail.length() < 6){
                val toastAlert = Toast.makeText(applicationContext, "Longitud minima de campo: 6", Toast.LENGTH_LONG)
                toastAlert.show()
            }
            if(!editTextEmail.text.toString().contains("@")){
                val toastAlert = Toast.makeText(applicationContext, " El email debe contener @", Toast.LENGTH_LONG)
                toastAlert.show()
            }
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(editTextEmail.text.toString(), editTextContrasena.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        val i = Intent(applicationContext, MenuActivity::class.java)
                        startActivity(i)
                    }else{
                        showAlertLogin()
                    }
                }
        }else{
            val toastAlert = Toast.makeText(applicationContext, "Error. Algún campo está vacío", Toast.LENGTH_LONG)
            toastAlert.show()
        }
    }
    private fun showAlertRegister(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error durante el registro.")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }
    private fun showAlertLogin(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Credenciales incorrectas.")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }
    override fun onBackPressed() {

    }
}
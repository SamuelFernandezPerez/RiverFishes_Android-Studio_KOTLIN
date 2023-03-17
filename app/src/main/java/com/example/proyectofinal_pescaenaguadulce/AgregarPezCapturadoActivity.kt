package com.example.proyectofinal_pescaenaguadulce

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarPezCapturadoActivity : AppCompatActivity() {
    lateinit var editTextClase : EditText
    lateinit var editTextPeso : EditText
    lateinit var editTextCebo : EditText
    lateinit var editTextZona : EditText
    lateinit var editTextLugar : EditText
    lateinit var editTextClima : EditText
    lateinit var opciones : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_pez_capturado)
        editTextClase = findViewById(R.id.editTextClase)
        editTextPeso = findViewById(R.id.editTextPeso)
        editTextCebo = findViewById(R.id.editTextCebo)
        editTextZona = findViewById(R.id.editTextZona)
        editTextLugar = findViewById(R.id.editTextLugar)
        editTextClima = findViewById(R.id.editTextClima)
        opciones = findViewById(R.id.spinnerGenero)
        val lista = listOf("Masculino", "Femenino", "Desconocido")
        val adaptador = ArrayAdapter(this, R.layout.spinner_item_genero, lista)
        opciones.adapter = adaptador
        opciones.onItemSelectedListener= object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long){
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val botonAgregar = findViewById<Button>(R.id.buttonAgregar)
        val font = Typeface.createFromAsset(assets, "fonts/fisherman.ttf")
        botonAgregar.typeface = font

        botonAgregar.setOnClickListener{
            nuevoPezCapturado()
        }
    }
    private fun nuevoPezCapturado() {
        val currentuseremail = FirebaseAuth.getInstance().currentUser?.email.toString()
        if(emptyFields()){
            val toastAlert = Toast.makeText(applicationContext, "No puedes dejar campos vacios", Toast.LENGTH_LONG)
            toastAlert.show()
        }else{
            val db = Firebase.firestore
            val pezCapturado = hashMapOf(
                "clase" to editTextClase.text.toString(),
                "peso" to editTextPeso.text.toString(),
                "cebo" to editTextCebo.text.toString(),
                "genero" to PezCapturado.Genero.valueOf(opciones.selectedItem.toString()),
                "zona" to editTextZona.text.toString(),
                "lugar" to editTextLugar.text.toString(),
                "clima" to editTextClima.text.toString(),
                "email_usuario" to currentuseremail
            )
            db.collection("pecesCapturados")
                .add(pezCapturado)
                .addOnSuccessListener { documentReference ->
                    val toastExitoAlert = Toast.makeText(applicationContext, "Pez añadido con exito", Toast.LENGTH_LONG)
                    toastExitoAlert.show()
                }
                .addOnFailureListener { e ->
                    val toastFracasoAlert = Toast.makeText(applicationContext, "Error $e durante la agregación", Toast.LENGTH_LONG)
                    toastFracasoAlert.show()
                }
            editTextClase.setText("")
            editTextPeso.setText("")
            editTextCebo.setText("")
            editTextZona.setText("")
            editTextLugar.setText("")
            editTextClima.setText("")
        }
    }
    private fun emptyFields(): Boolean{
        if(editTextClase.text.toString().isEmpty() || editTextPeso.text.toString().isEmpty() || editTextCebo.text.toString().isEmpty() || editTextZona.text.toString().isEmpty() || editTextLugar.text.toString().isEmpty() || editTextClima.text.toString().isEmpty()){
            return true
        }
        return false
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            Log.i("Info", "Se ha hecho clic en el botón home")
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {}
}
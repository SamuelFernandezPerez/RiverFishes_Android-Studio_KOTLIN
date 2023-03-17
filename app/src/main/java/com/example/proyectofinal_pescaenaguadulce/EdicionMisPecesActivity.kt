package com.example.proyectofinal_pescaenaguadulce

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EdicionMisPecesActivity : AppCompatActivity() {
    lateinit var editTextClase : EditText
    lateinit var editTextPeso : EditText
    lateinit var editTextCebo : EditText
    lateinit var editTextZona : EditText
    lateinit var editTextLugar : EditText
    lateinit var editTextClima : EditText
    lateinit var opciones : Spinner
    lateinit var pez : PezCapturado
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicion_mis_peces)
        editTextClase = findViewById(R.id.editTextClase)
        editTextPeso = findViewById(R.id.editTextPeso)
        editTextCebo = findViewById(R.id.editTextCebo)
        editTextZona = findViewById(R.id.editTextZona)
        editTextLugar = findViewById(R.id.editTextLugar)
        editTextClima = findViewById(R.id.editTextClima)
        opciones = findViewById(R.id.spinnerGenero)
        pez = intent.getSerializableExtra("pez") as PezCapturado
        editTextClase.setText(pez.clase)
        editTextPeso.setText(pez.peso.toString())
        editTextCebo.setText(pez.cebo)
        editTextZona.setText(pez.zona)
        editTextLugar.setText(pez.lugar)
        editTextClima.setText(pez.clima)
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
        opciones.setSelection(pez.genero.ordinal)

        val botonModificar = findViewById<Button>(R.id.buttonGuardar)
        botonModificar.setOnClickListener{
            modificarPez()
        }
        val botonEliminar = findViewById<Button>(R.id.buttonEliminar)
        botonEliminar.setOnClickListener{
            borrarPez()
            val i = Intent(applicationContext, ListadoMisPecesActivity::class.java)
            startActivity(i)
        }
    }
    private fun modificarPez() {
        if (emptyFields()) {
            val toastAlert = Toast.makeText(applicationContext, "No puedes dejar campos vacios", Toast.LENGTH_LONG)
            toastAlert.show()
        } else {
            db.collection("pecesCapturados").document(pez.id)
                .update(mapOf(
                    "clase" to editTextClase.text.toString(),
                    "peso" to editTextPeso.text.toString(),
                    "cebo" to editTextCebo.text.toString(),
                    "genero" to PezCapturado.Genero.valueOf(opciones.selectedItem.toString()),
                    "zona" to editTextZona.text.toString(),
                    "lugar" to editTextLugar.text.toString(),
                    "clima" to editTextClima.text.toString()
            ))
                .addOnSuccessListener {
                    val toastExitoAlert= Toast.makeText(applicationContext, "Pez modificado con exito", Toast.LENGTH_LONG
                    )
                    toastExitoAlert.show()
                }
                .addOnFailureListener { e ->
                    val toastFracasoAlert = Toast.makeText(applicationContext, "Error $e al modificar pez", Toast.LENGTH_LONG
                    )
                    toastFracasoAlert.show()
                }
        }
    }
    private fun borrarPez() {
        if (emptyFields()) {
            val toastAlert = Toast.makeText(applicationContext, "No puedes dejar campos vacios", Toast.LENGTH_LONG)
            toastAlert.show()
        } else {
            db.collection("pecesCapturados").document(pez.id)
                .delete()
                .addOnSuccessListener {
                    val toastExitoAlert= Toast.makeText(applicationContext, "Pez eliminado con exito", Toast.LENGTH_LONG
                    )
                    toastExitoAlert.show()
                }
                .addOnFailureListener { e ->
                    val toastFracasoAlert = Toast.makeText(applicationContext, "Error $e al eliminar pez", Toast.LENGTH_LONG
                    )
                    toastFracasoAlert.show()
                }
            val i = Intent(applicationContext, ListadoMisPecesActivity::class.java)
            startActivity(i)
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

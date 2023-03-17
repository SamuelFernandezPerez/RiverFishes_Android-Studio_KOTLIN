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
/**
 * Esta clase representa la actividad para agregar un nuevo pez capturado a la base de datos.
 * Contiene campos de texto para ingresar la clase, peso, cebo, zona,
 * lugar y clima del pez capturado,así como un spinner para seleccionar el género del pez.
 * Además, tiene un botón para agregar el nuevo pez.
 * Al agregar un nuevo pez, se verifica que todos los campos estén completos,
 * y si es así, se guarda la información en Firestore y se muestra un mensaje de éxito o error.
 * También se proporciona un botón de inicio para salir de la actividad.
 *
 * @constructor crea una instancia de la actividad AgregarPezCapturado.
 * @author [Samuel Fernandez Perez]
 */
class AgregarPezCapturadoActivity : AppCompatActivity() {
    lateinit var editTextClase : EditText
    lateinit var editTextPeso : EditText
    lateinit var editTextCebo : EditText
    lateinit var editTextZona : EditText
    lateinit var editTextLugar : EditText
    lateinit var editTextClima : EditText
    lateinit var opciones : Spinner

    /**
     * Función que se llama cuando se crea la actividad.
     * Se inicializan los campos de texto y el spinner y se asigna el adaptador del spinner
     * para mostrar las opciones de género.
     * También se establece el tipo de fuente para el botón de agregar
     * y se define su comportamiento cuando se hace clic en él.
     *
     * @param objeto savedInstanceState [Bundle] que contiene el estado anterior de la actividad.
     */
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
    /**
     * Función que se llama cuando se hace clic en el botón de agregar.
     * Se verifica que todos los campos estén completados y, si es así,
     * se guarda la información del nuevo pez en Firestore y se muestra un mensaje de éxito
     * o error. Si hay campos vacíos, se muestra un mensaje de alerta.
     */
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
    /**
     * Verifica si alguno de los campos de texto está vacío.
     *
     * @return true si algún campo está vacío, false en caso contrario.
     */
    private fun emptyFields(): Boolean{
        if(editTextClase.text.toString().isEmpty() || editTextPeso.text.toString().isEmpty() || editTextCebo.text.toString().isEmpty() || editTextZona.text.toString().isEmpty() || editTextLugar.text.toString().isEmpty() || editTextClima.text.toString().isEmpty()){
            return true
        }
        return false
    }
    /**
     * Este método se llama cuando se selecciona un elemento del menú de opciones.
     * En este caso, se verifica si el elemento seleccionado es el botón Home y se
     * imprime un mensaje de información en el Log.
     *
     * @param item elemento seleccionado del menú de opciones.
     * @return true si se ha gestionado correctamente la selección del elemento, false en caso contrario.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            Log.i("Info", "Se ha hecho clic en el botón home")
        }
        return super.onOptionsItemSelected(item)
    }
    /**
     * Esta función anula el comportamiento predeterminado del botón Atrás cuando se presiona.
     * De forma predeterminada, el botón Atrás cierra la actividad actual
     * y vuelve a la anterior en la pila de actividades.
     */
    override fun onBackPressed() {}
}
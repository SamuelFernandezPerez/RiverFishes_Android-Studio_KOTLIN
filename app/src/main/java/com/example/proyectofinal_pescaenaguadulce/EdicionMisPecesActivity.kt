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
/**
 * Esta clase es responsable de la actividad de edición de un pez capturado,
 * permitiendo modificar y eliminar los datos del pez.
 * La actividad muestra un formulario con campos para ingresar y editar la información del pez,
 * incluyendo su clase, peso, cebo utilizado, género, zona de captura, lugar de captura y clima.
 * El formulario también incluye botones para guardar los cambios realizados en el pez
 * y para eliminar el pez de la base de datos.
 * En caso de que algun campo este vacio al intentar guardar o eliminar el pez,
 * se muestra una alerta en pantalla indicando que no se pueden dejar campos vacios.
 *
 * @constructor Crea una instancia de la clase [EdicionMisPecesActivity]
 * @author [Samuel Fernández Pérez]
 */
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

    /**
     * Esta función anula el método onCreate() predeterminado de la clase Activity.
     * Establece el diseño de la actividad a R.layout.activity_edicion_mis_peces
     * e inicializa las vistas correspondientes a los widgets EditText y Spinner.
     * Recupera el objeto PezCapturado de la intención y completa las vistas EditText y Spinner
     * con sus datos. También establece onItemSelectedListener para el widget Spinner.
     * Finalmente, configura el onClickListener para los botones "Guardar" y "Eliminar".
     *
     * @param objeto savedInstanceState [Bundle] que contiene el estado anterior de la actividad.
     */
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
    /**
     * Modifica los datos del pez capturado actualmente seleccionado por el usuario.
     * Si algun campo esta vacio, muestra un mensaje de error.
     * Si se completa la modificación con éxito, muestra un mensaje de éxito.
     * Si falla la modificación, muestra un mensaje de error.
     */
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
    /**
     * Función encargada de borrar un pez capturado de la base de datos de Firebase Firestore.
     * Si hay algún campo vacío, muestra un mensaje de error mediante un Toast.
     * Si se borra el pez con éxito, muestra un Toast con un mensaje de éxito
     * y redirige al usuario a la actividad ListadoMisPecesActivity.
     * Si ocurre un error al borrar el pez, muestra un Toast con el mensaje de error correspondiente.
     */
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

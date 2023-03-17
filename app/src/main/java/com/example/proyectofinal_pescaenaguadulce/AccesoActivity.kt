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
/**
 * Esta clase controla la vista y funcionalidades de la pantalla de acceso de la aplicación.
 * Esta clase hereda de AppCompatActivityy contiene métodos para el registro
 * y acceso de usuarios a la aplicación.
 * También incluye la validación de los campos de correo electrónico y contraseña,
 * así como la verificación de las credenciales de acceso.
 *
 * @constructor crea una instancia de la actividad y configura la vista con el diseño correspondiente.
 * @author [Samuel Fernandez Perez]
 */
class AccesoActivity : AppCompatActivity() {
    /**
     * Función que se ejecuta cuando se crea la actividad Acceso.
     * Se establece el diseño, se asigna la fuente personalizada a los botones de registro y acceso,
     * se les asigna una acción al hacer clic y se verifica si ya hay un usuario logueado.
     * Si ya hay un usuario logueado, se redirigirá automáticamente a la actividad del menú.
     *
     * @param objeto savedInstanceState [Bundle] que contiene el estado anterior de la actividad.
     */
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
    /**
     * Función para registrar un nuevo usuario con correo electrónico
     * y contraseña usando Firebase Authentication.
     *
     * @return Nothing
     */
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
    /**
     * Función para autenticar a un usuario con correo electrónico y
     * contraseña usando Firebase Authentication y redirigirlos a MenuActivity
     * si la autenticación es exitosa.
     *
     * La función verifica si los campos de correo electrónico y contraseña no están vacíos
     * y tienen una longitud mínima de 6 caracteres. Si el campo de correo electrónico
     * no contiene "@", se muestra un mensaje de error al usuario.
     *
     * La función utiliza FirebaseAuth para autenticar al usuario con el correo electrónico
     * y la contraseña proporcionados,e inicia MenuActivity si la autenticación es exitosa.
     * Si la autenticación falla, la función llama a showAlertLogin para mostrar un mensaje
     * de error al usuario.
     *
     * @return Nothing
     */

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
    /**
     * Función para mostrar un cuadro de diálogo de alerta con un mensaje de error
     * al usuario cuando falla el registro.
     * La función crea un AlertDialog con un título y mensaje de error y un botón "Aceptar".
     * La función se llama cuando falla el registro del usuario y se debe informar
     * al usuario sobre el error.
     *
     * @return Nothing
     */
    private fun showAlertRegister(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error durante el registro.")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }
    /**
     * Función para mostrar un cuadro de diálogo de alerta con un mensaje de error
     * para el usuario cuando falla el inicio de sesión.
     * La función crea un AlertDialog con un título y mensaje de error y un botón "Aceptar".
     * La función se llama cuando falla el inicio de sesión del usuario y
     * se debe informar al usuario sobre el error.
     *
     * @return Nothing
     */
    private fun showAlertLogin(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Credenciales incorrectas.")
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }
    /**
     * Esta función anula el comportamiento predeterminado del botón Atrás cuando se presiona.
     * De forma predeterminada, el botón Atrás cierra la actividad actual
     * y vuelve a la anterior en la pila de actividades.
     */
    override fun onBackPressed() {

    }

}
package com.example.proyectofinal_pescaenaguadulce

/**
 * Esta clase representa un pez capturado con información adicional.
 * Hereda de la clase Pez y agrega atributos como peso, género, lugar, clima, cebo e ID.
 *
 * @property peso El peso del pez capturado.
 * @property genero El género del pez capturado, que puede ser masculino, femenino o desconocido.
 * @property lugar El lugar donde se capturó el pez.
 * @property clima El clima durante la captura del pez.
 * @property cebo El cebo utilizado para la captura del pez.
 * @property id El identificador único del pez capturado.
 * @constructor Crea un nuevo objeto PezCapturado con la clase, la zona, el peso, el género, el lugar, el clima, el cebo y el ID dados.
 * @param clase El tipo o clase de pez.
 * @param zona La zona de pesca donde se capturó el pez.
 * @inherits Pez Clase de la que hereda PezCapturado.
 * @enum Genero Enumeración que contiene los posibles valores para el género del pez capturado.
 * @implements Serializable Interfaz que indica que esta clase puede ser serializada para ser almacenada en archivos u otros medios.
 * @author [Samuel Fernández Pérez]
 */
class PezCapturado(clase: String, zona: String, var peso : Float, var genero : Genero,  var lugar : String, var clima : String, var cebo : String, var id : String)  : Pez(clase, zona)   {
    enum class Genero {
        Masculino, Femenino, Desconocido
    }
}



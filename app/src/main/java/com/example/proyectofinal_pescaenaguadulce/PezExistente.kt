package com.example.proyectofinal_pescaenaguadulce

/**
 * Esta clase representa un pez existente, con una imagen que lo identifica.
 * Hereda de la clase Pez y agrega un atributo que es la imagen del pez.
 *
 * @property imagePez La imagen que identifica al pez existente.
 * @constructor Crea un nuevo objeto PezExistente con la clase, la zona y la imagen del pez dados.
 * @param clase El tipo o clase de pez.
 * @param zona La zona de pesca donde se puede encontrar el pez.
 * @inherits Pez Clase de la que hereda PezExistente.
 * @implements Serializable Interfaz que indica que esta clase puede ser serializada para ser almacenada en archivos u otros medios.
 * @author [Samuel Fernández Pérez]
 */
class PezExistente(clase: String, zona: String, var imagePez : Int) : Pez(clase, zona) {
}



package com.example.proyectofinal_pescaenaguadulce

import java.io.Serializable

/**
 * Esta es una clase abierta que representa un pez capturado.
 *
 * @property clase El tipo o clase de pez.
 * @property zona La zona de pesca donde se capturó el pez.
 * @constructor Crea un nuevo objeto Pez con la clase y la zona dadas.
 * @implements Serializable Interfaz que indica que esta clase puede ser serializada para ser almacenada en archivos u otros medios.
 * @author [Samuel Fernández Pérez]
 */
open class Pez(var clase: String, var zona: String) : Serializable



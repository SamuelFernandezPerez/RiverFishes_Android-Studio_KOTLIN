package com.example.proyectofinal_pescaenaguadulce

/**
 * Esta clase representa una zona de pesca, con su nombre, lugar, imagen y mapa asociados.
 *
 * @property nombre El nombre de la zona de pesca.
 * @property lugar El lugar donde se encuentra la zona de pesca.
 * @property imageZona La imagen que representa la zona de pesca.
 * @property imageMap La imagen del mapa de la zona de pesca.
 * @constructor Crea un nuevo objeto Zona con el nombre, lugar,imagen y mapa de la zona de pesca dados.
 * @implements Serializable Interfaz que indica que esta clase puede ser serializada para ser almacenada en archivos u otros medios.
 * @return Una instancia de la clase Zona.
 * @version 1.0
 * @author [Samuel Fernández Pérez]
 */
data class Zona(var nombre : String, var lugar: String, var imageZona : Int, var imageMap : Int)


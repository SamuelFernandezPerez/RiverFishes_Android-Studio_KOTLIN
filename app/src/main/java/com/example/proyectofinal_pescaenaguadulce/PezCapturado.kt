package com.example.proyectofinal_pescaenaguadulce

class PezCapturado(clase: String, zona: String, var peso : Float, var genero : Genero,  var lugar : String, var clima : String, var cebo : String, var id : String)  : Pez(clase, zona)   {
    enum class Genero {
        Masculino, Femenino, Desconocido
    }
}



package com.app.myapplication.models

data class Empleado(var nombre:String, var calificacion:String, var comentario:String) {
    constructor():this("","",""){
        this.nombre=nombre
        this.calificacion=calificacion
        this.comentario=comentario
    }
}
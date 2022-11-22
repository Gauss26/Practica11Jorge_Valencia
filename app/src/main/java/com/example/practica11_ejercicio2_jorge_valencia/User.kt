package com.example.practica11_ejercicio2_jorge_valencia

import java.io.Serializable

class User: Serializable {

    var name=""
    var email=""
    var password = ""
    var rol = ""

    constructor(name:String, email:String, password: String, rol:String) {
        this.name = name
        this.email = email
        this.password = password
        this.rol = rol
    }
    public override fun toString(): String {
        return "Nombre: "+name+"\nCorreo: "+email+"\nRol: "+rol
    }
}
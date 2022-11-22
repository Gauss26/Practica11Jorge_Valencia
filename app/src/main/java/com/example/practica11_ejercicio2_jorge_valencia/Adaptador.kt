package com.example.practica11_ejercicio2_jorge_valencia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Adaptador  : ArrayAdapter<User> {

    private var users: List<User>

    constructor(context: Context, lista: List<User>)
            : super(context, R.layout.item, lista) {
        users = lista
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val convertView = inflater.inflate(R.layout.item, null)

        val textViewMarca = convertView.findViewById<TextView>(R.id.name_view)
        val textViewEmail = convertView.findViewById<TextView>(R.id.email_view)
        val textViewRol = convertView.findViewById<TextView>(R.id.rol_view)

        textViewMarca.text = users[position].name
        textViewEmail.text = users[position].email
        textViewRol.text = users[position].rol



        return convertView
    }
}
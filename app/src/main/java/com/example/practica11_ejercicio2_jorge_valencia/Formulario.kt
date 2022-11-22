package com.example.practica11_ejercicio2_jorge_valencia

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity

class Formulario : AppCompatActivity(),OnItemSelectedListener, OnClickListener {
    private lateinit var  name : EditText
    private lateinit var  email: EditText
    private lateinit var  pass : EditText
    private lateinit var  rol : Spinner
    private lateinit var  send : Button

    var rol_save = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        send = findViewById(R.id.send)
        rol = findViewById(R.id.rol)

        rol.adapter = ArrayAdapter.createFromResource(this,R.array.rol,android.R.layout.simple_list_item_1)

        if (intent.hasExtra("userModify")){
            var user_modi = intent?.getSerializableExtra("userModify") as User
            name.setText(user_modi.name)
            email.setText(user_modi.email)
            pass.setText(user_modi.password)
            rol.setSelection(buscarPos(user_modi.rol))
        }
        send.setOnClickListener(this)
        rol.onItemSelectedListener = this
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.send ){
            if(intent.hasExtra("position")){
                intent.putExtra("user", User(name.text.toString(),email.text.toString(),pass.text.toString(),rol_save))
                intent.putExtra("position", intent.extras?.getInt("position"))
                setResult(RESULT_CANCELED,intent)
            }else{
            intent.putExtra("user", User(name.text.toString(),email.text.toString(),pass.text.toString(),rol_save))
            setResult(RESULT_OK,intent)
            }
            finish()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            rol_save = parent?.adapter?.getItem(position).toString();

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
    private fun buscarPos(rol_name: String): Int {
        for (i in 0..rol?.adapter?.count!!) {
            if (rol?.adapter?.getItem(i)?.toString().equals(rol_name))
                return i
        }
        return 0
    }
}


package com.example.practica11_ejercicio2_jorge_valencia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class Detalles : AppCompatActivity(), OnClickListener{
    private lateinit var tv1 :TextView
    private lateinit var tv2 : TextView
    private lateinit var tv3 :TextView
    private lateinit var back : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        tv1 = findViewById(R.id .name_details)
        tv2 = findViewById(R.id.email_details)
        tv3 = findViewById(R.id.rol_details)
        var user = intent.getSerializableExtra("user") as User
        tv1.text = user.name
        tv2.text = user.email
        tv3.text = user.rol
        back=findViewById(R.id.back)
        back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.back){
            var intent_back = Intent(this, MainActivity::class.java )
            startActivity(intent_back)
         }
    }
}
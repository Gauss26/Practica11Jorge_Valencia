package com.example.practica11_ejercicio2_jorge_valencia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf


class MainActivity : AppCompatActivity() {
    private lateinit var lista : ListView
    var us = arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        us.add (User("Jorge","Jorge@emial.com","1234","Admin"))
        lista = findViewById(R.id.lv)
        lista.adapter = Adaptador(this,us)
        registerForContextMenu(lista)
    }

    override fun onResume() {
        super.onResume()
        lista.adapter = Adaptador(this,us)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_opciones,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.reset ->{
                us.clear()
                onResume()
            }
            R.id.add ->{
                var intent = Intent(this, Formulario::class.java)
                startForResult.launch(intent)
                onResume()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.menu_contextual,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterContextMenuInfo

        when(item.itemId){
            R.id.details->{
                var intent_details = Intent (this, Detalles::class.java).apply {
                    putExtra("user",us[info.position])
                }
                startActivity(intent_details)
            }
            R.id.delete ->{
                us.removeAt(info.position)
                onResume()
            }
            R.id.modify ->{
                var intent_modi = Intent(this, Formulario::class.java).apply {

                    putExtra("userModify",us[info.position])
                    putExtra("position", info.position)

                }
                startForResult.launch(intent_modi)
                onResume()
            }
        }
        return super.onContextItemSelected(item)
    }
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result : ActivityResult ->
        if(result.resultCode == RESULT_OK) {
            us.add(result.data?.extras?.getSerializable("user") as User)
            onResume()
        }else if (result.resultCode == RESULT_CANCELED){
            us[result.data?.extras?.getInt("position")!!] = result.data?.extras?.getSerializable("user") as User
        }
    }
}
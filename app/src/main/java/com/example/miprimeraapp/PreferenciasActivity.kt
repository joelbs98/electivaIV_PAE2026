package com.example.miprimeraapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PreferenciasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preferencias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombrePreferencia)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarPreferencia)
        val tvNombreGuardado = findViewById<TextView>(R.id.tvNombreGuardado)

        val preferencias = getSharedPreferences("MisPreferencias",MODE_PRIVATE)
        val nombreGuardado = preferencias.getString("nombre","")
        if(!nombreGuardado.isNullOrEmpty()){
            tvNombreGuardado.text = nombreGuardado
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            if(nombre.isEmpty()){
                etNombre.error = "Ingrese su nombre"
                return@setOnClickListener
            }
            val editor = preferencias.edit()
            editor.putString("nombre",nombre)
            editor.apply()
            //eliminar todos los datos almacenados en preferences -> clear() -> editor.clear()
            //eliminar una clave especifica -> remove() -> editor.remove("nombre")
            //no olvidar que luego de hacer alguna operacion con las sp debemos hacer apply()
            tvNombreGuardado.text = nombre
            Toast.makeText(this,R.string.preferencias_guardado_exitoso,
                Toast.LENGTH_SHORT).show()
        }
    }
}
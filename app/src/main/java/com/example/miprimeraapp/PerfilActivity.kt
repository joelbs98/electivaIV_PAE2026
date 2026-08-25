package com.example.miprimeraapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvCarrera = findViewById<TextView>(R.id.tvCarrera)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        val nombre = intent.getStringExtra("nombre")
        val carrera = intent.getStringExtra("carrera")

        tvNombre.text = nombre
        tvCarrera.text = carrera

        btnVolver.setOnClickListener {
            finish()
        }

    }
}
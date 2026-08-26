package com.example.miprimeraapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EstudiantesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estudiantes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvEstudiantes)
        val estudiantes = listOf(
            Estudiante(
                "Joel Barba",
                "Ingenieria en sistemas",
                8
            ),
            Estudiante(
                "Alejandro Barba",
                "Ingenieria en sistemas",
                7
            ),
            Estudiante(
                "Angie Coello",
                "Ingenieria en sistemas",
                5
            ),
            Estudiante(
                "Elon Musk",
                "Software",
                1
            )
        )
        val adapter = EstudianteAdapter(estudiantes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
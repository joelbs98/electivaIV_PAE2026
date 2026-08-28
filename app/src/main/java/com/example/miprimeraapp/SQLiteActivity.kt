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

class SQLiteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sqlite)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etId =
            findViewById<EditText>(
                R.id.etIdEstudiante
            )

        val etNombre =
            findViewById<EditText>(
                R.id.etNombreEstudiante
            )

        val etCarrera =
            findViewById<EditText>(
                R.id.etCarreraEstudiante
            )

        val btnInsertar =
            findViewById<Button>(
                R.id.btnInsertarEstudiante
            )

        val btnConsultar =
            findViewById<Button>(
                R.id.btnConsultarEstudiantes
            )

        val btnActualizar =
            findViewById<Button>(
                R.id.btnActualizarEstudiante
            )

        val btnEliminar =
            findViewById<Button>(
                R.id.btnEliminarEstudiante
            )

        val tvLista =
            findViewById<TextView>(
                R.id.tvListaEstudiantes
            )

        val baseDatos = BaseDatosHelper(this)

        btnInsertar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val carrera = etCarrera.text.toString().trim()
            if(nombre.isEmpty() || carrera.isEmpty()){
                Toast.makeText(this,"Complete nombre y carrera"
                    ,Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val resultado = baseDatos.InsertarEstudiante(nombre,carrera)
            if(resultado != -1L){
                Toast.makeText(this,"Estudiante registrado",Toast.LENGTH_SHORT).show()
                etNombre.text.clear()
                etCarrera.text.clear()
            }else{
                Toast.makeText(this,"Error al registrar",Toast.LENGTH_SHORT).show()
            }

        }
        btnConsultar.setOnClickListener {
            val estudiantes = baseDatos.obtenerEstudiantes()
            tvLista.text = estudiantes
        }
        btnActualizar.setOnClickListener {

            val idTexto =
                etId.text
                    .toString()
                    .trim()

            val nombre =
                etNombre.text
                    .toString()
                    .trim()

            val carrera =
                etCarrera.text
                    .toString()
                    .trim()

            if (
                idTexto.isEmpty() ||
                nombre.isEmpty() ||
                carrera.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Complete ID, nombre y carrera",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val id =
                idTexto.toInt()

            val filas =
                baseDatos.actualizarEstudiante(
                    id,
                    nombre,
                    carrera
                )

            if (filas > 0) {

                Toast.makeText(
                    this,
                    "Estudiante actualizado",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                Toast.makeText(
                    this,
                    "No se encontró el estudiante",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnEliminar.setOnClickListener {

            val idTexto =
                etId.text
                    .toString()
                    .trim()

            if (idTexto.isEmpty()) {

                etId.error =
                    "Ingrese el ID"

                return@setOnClickListener
            }

            val id =
                idTexto.toInt()

            val filas =
                baseDatos.eliminarEstudiante(
                    id
                )

            if (filas > 0) {

                Toast.makeText(
                    this,
                    "Estudiante eliminado",
                    Toast.LENGTH_SHORT
                ).show()

                etId.text.clear()

            } else {

                Toast.makeText(
                    this,
                    "No se encontró el estudiante",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
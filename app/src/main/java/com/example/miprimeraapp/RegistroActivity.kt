package com.example.miprimeraapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre =
            findViewById<EditText>(R.id.etNombre)

        val etCorreo =
            findViewById<EditText>(R.id.etCorreo)

        val rgModalidad =
            findViewById<RadioGroup>(R.id.rgModalidad)

        val btnFecha =
            findViewById<Button>(R.id.btnFecha)

        val tvFecha =
            findViewById<TextView>(R.id.tvFecha)

        val cbTerminos =
            findViewById<CheckBox>(R.id.cbTerminos)

        val swNotificaciones =
            findViewById<Switch>(R.id.swNotificaciones)

        val btnRegistrar =
            findViewById<Button>(R.id.btnRegistrar)

        btnFecha.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val selectorFecha =
                DatePickerDialog(
                    this,
                    { _, anioSeleccionado, mesSeleccionado, diaSeleccionado ->

                        val fecha =
                            "$diaSeleccionado/${mesSeleccionado + 1}/$anioSeleccionado"

                        tvFecha.text = fecha

                    },
                    anio,
                    mes,
                    dia
                )
            selectorFecha.show()
        }

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val correo = etCorreo.text.toString()

            if(nombre.isEmpty()){
                etNombre.error = getString(R.string.error_nombre)
                return@setOnClickListener
            }
            if(correo.isEmpty()){
                etCorreo.error = getString(R.string.error_correo)
                return@setOnClickListener
            }

            val modalidad = when(rgModalidad.checkedRadioButtonId){
                R.id.rbPresencial -> "Presencial"
                R.id.rbVirtual -> "Virtual"
                else -> "No seleccionada"
            }

            if(!cbTerminos.isChecked){
                Toast.makeText(this, "Debe aceptar los terminos y condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val notificaciones = if(swNotificaciones.isChecked) {
                "Activadas"
            }else{
                "Desactivadas"
            }

            val fecha = tvFecha.text.toString()

            val mensaje = """ 
                Nombre: $nombre
                
                Correo: $correo
                
                Modalidad: $modalidad
                
                Fecha: $fecha
                
                Notificaciones: $notificaciones
                
                """.trimIndent()

            AlertDialog.Builder(this)
                .setTitle(R.string.confirmar_registro)
                .setMessage(mensaje)
                .setPositiveButton(R.string.aceptar) { _, _ ->

                    Toast.makeText(
                        this,
                        R.string.registro_exitoso,
                        Toast.LENGTH_LONG
                    ).show()
                }
                .setNegativeButton(R.string.cancelar, null)
                .show()

        }
    }
}
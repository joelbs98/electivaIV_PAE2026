package com.example.miprimeraapp

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfiguracionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_configuracion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val swNotificaciones = findViewById<Switch>(R.id.swNotificaciones)
        val swSincronizacion = findViewById<Switch>(R.id.swSincronizacion)
        val swModoOscuro = findViewById<Switch>(R.id.swModoOscuro)
        val rgTamanoTexto = findViewById<RadioGroup>(R.id.rgTamanoTexto)
        val rbPequeno = findViewById<RadioButton>(R.id.rbPequeno)
        val rbNormal = findViewById<RadioButton>(R.id.rbNormal)
        val rbGrande = findViewById<RadioButton>(R.id.rbGrande)
        val btnRestaurar = findViewById<Button>(R.id.btnRestaurar)
        val tvEstado = findViewById<TextView>(R.id.tvEstadoConfiguracion)

        val preferencias = getSharedPreferences("ConfiguracionApp",MODE_PRIVATE)
        val notificaciones = preferencias.getBoolean("notificaciones",true)
        val sincronizacion = preferencias.getBoolean("sincronizacion",true)
        val modoOscuro = preferencias.getBoolean("modoOscuro", false)
        val tamanoTexto = preferencias.getString("tamanoTexto","normal")

        swNotificaciones.isChecked = notificaciones
        swSincronizacion.isChecked = sincronizacion
        swModoOscuro.isChecked = modoOscuro
        when (tamanoTexto){
            "pequeno" -> rbPequeno.isChecked = true
            "grande" -> rbGrande.isChecked = true
            else -> rbNormal.isChecked = true
        }

        swNotificaciones.setOnCheckedChangeListener { _, activado ->
            preferencias.edit().putBoolean("notificaciones",activado).apply()
            tvEstado.text = "Configuración actualizada"
        }
        swModoOscuro.setOnCheckedChangeListener { _, activado ->
            preferencias.edit().putBoolean("modoOscuro",activado).apply()
            tvEstado.text = "Configuración actualizada"
        }
        swSincronizacion.setOnCheckedChangeListener { _, activado ->
            preferencias.edit().putBoolean("sincronizacion",activado).apply()
            tvEstado.text = "Configuración actualizada"
        }
        rgTamanoTexto.setOnCheckedChangeListener { _, checkedId ->
            val tamano = when(checkedId){
                R.id.rbPequeno -> "pequeno"
                R.id.rbGrande -> "grande"
                else -> "normal"
            }
            preferencias.edit().putString("tamanoTexto",tamano).apply()
            tvEstado.text = "Configuración actualizada"
        }
        btnRestaurar.setOnClickListener {
            preferencias.edit().clear().apply()
            swNotificaciones.isChecked = true
            swSincronizacion.isChecked = true
            swModoOscuro.isChecked = false
            rbNormal.isChecked = true
            tvEstado.text = "Configuración restaurada"
            Toast.makeText(this, R.string.config_restaurada,
                Toast.LENGTH_SHORT).show()
        }

    }
}
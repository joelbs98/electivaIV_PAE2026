package com.example.miprimeraapp

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class ServicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_servicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvEstado = findViewById<TextView>(R.id.tvEstadoServicio)
        val btnIniciar = findViewById<Button>(R.id.btnIniciarServicio)
        val btnDetener = findViewById<Button>(R.id.btnDetenerServicio)

        val intentServicio = Intent(this, ContadorService::class.java)
        btnIniciar.setOnClickListener {
            startService(intentServicio)
            tvEstado.text = getString(R.string.servicio_estado_activo)
            Toast.makeText(this,"Servicio iniciado",Toast.LENGTH_SHORT).show()
        }
        btnDetener.setOnClickListener {
            stopService(intentServicio)
            tvEstado.text = getString(R.string.servicio_estado_detenido)
            Toast.makeText(this,"Servicio detenido",Toast.LENGTH_SHORT).show()
        }

    }
}
package com.example.miprimeraapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CargaDatosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carga_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCargar = findViewById<Button>(R.id.btnCargarDatos)
        val progressCarga = findViewById<ProgressBar>(R.id.progressCarga)
        val tvEstado = findViewById<TextView>(R.id.tvEstadoCarga)
        val tvResultado = findViewById<TextView>(R.id.tvResultadoCarga)

        val baseDatos = BaseDatosHelper(this)

        btnCargar.setOnClickListener {
            progressCarga.visibility = View.VISIBLE
            tvEstado.text = getString(R.string.carga_espera)
            tvResultado.text = ""

            lifecycleScope.launch {
                val datos = withContext(Dispatchers.IO){
                    Thread.sleep(2000)
                    baseDatos.obtenerEstudiantes()
                }
                tvResultado.text = datos
                progressCarga.visibility = View.GONE
                tvEstado.text = "Carga finalizada"
            }
        }
    }
}
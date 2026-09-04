package com.example.miprimeraapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class DescargaActivity : AppCompatActivity() {
    private var trabajoDescarga: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descarga)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val progressDescarga = findViewById<ProgressBar>(R.id.progressDescarga)
        val tvPorcentaje = findViewById<TextView>(R.id.tvPorcentajeDescarga)
        val tvEstado = findViewById<TextView>(R.id.tvEstadoDescarga)
        val btnIniciar = findViewById<Button>(R.id.btnIniciarDescarga)
        val btnCancelar = findViewById<Button>(R.id.btnCancelarDescarga)

        btnIniciar.setOnClickListener {
            progressDescarga.progress = 0
            tvPorcentaje.text = "0 %"
            tvEstado.text = getString(R.string.descarga_proceso)
            btnIniciar.isEnabled = false
            btnCancelar.isEnabled = true
            trabajoDescarga = lifecycleScope.launch {
                for(progreso in 10..100 step 10){
                    val nuevoProgreso = withContext(Dispatchers.Default){
                        delay(400)
                        progreso
                    }
                    progressDescarga.progress = nuevoProgreso
                    tvPorcentaje.text = "$nuevoProgreso%"

                }
                tvEstado.text = getString(R.string.descarga_finalizada)
                btnIniciar.isEnabled=true
                btnCancelar.isEnabled=false

            }
        }
        btnCancelar.setOnClickListener {
            trabajoDescarga?.cancel()
            tvEstado.text = getString(R.string.descarga_cancelada)
            btnIniciar.isEnabled = true
            btnCancelar.isEnabled = false
        }
    }
}
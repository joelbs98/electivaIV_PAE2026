package com.example.miprimeraapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val TAG = "CICLO_MAIN"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSaludar = findViewById<Button>(R.id.btnSaludar)

        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnSaludar.setOnClickListener {
            tvResultado.text = getString(R.string.mensaje_bienvenida)
        }

        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)
        val tvEstadoLectura = findViewById<TextView>(R.id.tvEstadoLectura)

        btnFinalizar.setOnClickListener {
            tvEstadoLectura.text = getString(R.string.lectura_completada)
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://developer.android.com"))
            startActivity(intent)
        }

        val btnDetalle = findViewById<Button>(R.id.btnDetalle)
        btnDetalle.setOnClickListener {
            val intent = Intent(this, DetalleActivity::class.java)

            //intent.putExtra("nombre", "Estudiante Joel")

            startActivity(intent)
            //intent explicito

        }


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
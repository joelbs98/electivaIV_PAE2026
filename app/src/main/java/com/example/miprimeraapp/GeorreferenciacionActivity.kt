package com.example.miprimeraapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class GeorreferenciacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_georreferenciacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etLatitud =
            findViewById<EditText>(
                R.id.etLatitud
            )

        val etLongitud =
            findViewById<EditText>(
                R.id.etLongitud
            )

        val btnMostrar =
            findViewById<Button>(
                R.id.btnMostrarCoordenadas
            )

        val btnMapa =
            findViewById<Button>(
                R.id.btnAbrirMapa
            )

        val tvResultado =
            findViewById<TextView>(
                R.id.tvResultadoGeo
            )
        btnMostrar.setOnClickListener {

            val coordenadas =
                obtenerCoordenadas(
                    etLatitud,
                    etLongitud
                )
                    ?: return@setOnClickListener

            val latitud =
                coordenadas.first

            val longitud =
                coordenadas.second

            tvResultado.text =
                """
        Ubicación seleccionada

        Latitud: $latitud
        Longitud: $longitud
        """.trimIndent()
        }
        btnMapa.setOnClickListener {
            val coordenadas = obtenerCoordenadas(
                etLatitud,etLongitud
            ) ?: return@setOnClickListener
            val latitud = coordenadas.first
            val longitud = coordenadas.second
            val uri = Uri.parse("geo:0,0?q=$latitud,$longitud")
            val intentMapa = Intent(Intent.ACTION_VIEW,uri)
            if(intentMapa.resolveActivity(packageManager)!=null){
                startActivity(intentMapa)
            }else{
                Toast.makeText(this,"No existe una app de mapas instalada"
                    ,Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun obtenerCoordenadas(
        etLatitud: EditText,
        etLongitud: EditText
    ): Pair<Double, Double>? {

        val latitud =
            etLatitud.text
                .toString()
                .trim()
                .toDoubleOrNull()

        val longitud =
            etLongitud.text
                .toString()
                .trim()
                .toDoubleOrNull()

        if (
            latitud == null ||
            longitud == null
        ) {

            Toast.makeText(
                this,
                "Ingrese coordenadas válidas",
                Toast.LENGTH_SHORT
            ).show()

            return null
        }

        if (
            latitud !in -90.0..90.0
        ) {

            etLatitud.error =
                "Latitud entre -90 y 90"

            return null
        }

        if (
            longitud !in -180.0..180.0
        ) {

            etLongitud.error =
                "Longitud entre -180 y 180"

            return null
        }

        return Pair(
            latitud,
            longitud
        )
    }
}
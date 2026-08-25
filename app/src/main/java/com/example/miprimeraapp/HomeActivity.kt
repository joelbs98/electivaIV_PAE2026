package com.example.miprimeraapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnPerfil = findViewById<Button>(R.id.btnPerfil)
        val btnWeb = findViewById<Button>(R.id.btnWeb)
        val btnLlamar = findViewById<Button>(R.id.btnLlamar)

        btnPerfil.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            intent.putExtra("nombre", "Estudiante demo")
            intent.putExtra("carrera", "Software")
            startActivity(intent)
        }

        btnWeb.setOnClickListener {
            val pagina =
                Uri.parse("https://developer.android.com")

            val intent =
                Intent(Intent.ACTION_VIEW, pagina)

            startActivity(intent)
        }

        btnLlamar.setOnClickListener {
            val numero = Uri.parse("tel:0999999999")
            val intent = Intent(Intent.ACTION_DIAL,numero)
            startActivity(intent)

        }

    }
}
package com.example.miprimeraapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbarMenu)
        toolbar.title = getString(R.string.menu_titulo)
        toolbar.inflateMenu(R.menu.menu_principal)

        toolbar.setOnMenuItemClickListener {
            item ->
            when (item.itemId){
                R.id.menu_perfil -> {
                    Toast.makeText(this, R.string.mensaje_perfil,Toast.LENGTH_SHORT).show()

                }
                R.id.menu_configuracion -> {
                    val intent = Intent(this, RegistroActivity::class.java)
                    startActivity(intent)

                }
                R.id.menu_acerca -> {

                }
            }
            true
        }


    }
}
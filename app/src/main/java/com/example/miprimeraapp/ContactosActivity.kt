package com.example.miprimeraapp

import ContactoAdapter
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactosActivity : AppCompatActivity() {

    private val listaContactos =
        mutableListOf<Contacto>()

    private lateinit var adapter:
            ContactoAdapter

    private val solicitarPermisoContactos =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { concedido ->

            if (concedido) {

                cargarContactos()

            } else {

                Toast.makeText(
                    this,
                    "Permiso de contactos denegado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_contactos
        )

        val btnCargar =
            findViewById<Button>(
                R.id.btnCargarContactos
            )

        val rvContactos =
            findViewById<RecyclerView>(
                R.id.rvContactos
            )

        adapter =
            ContactoAdapter(
                listaContactos
            )

        rvContactos.layoutManager =
            LinearLayoutManager(this)

        rvContactos.adapter =
            adapter

        btnCargar.setOnClickListener {

            verificarPermisoContactos()
        }
    }

    private fun verificarPermisoContactos() {

        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            cargarContactos()

        } else {

            solicitarPermisoContactos.launch(
                Manifest.permission.READ_CONTACTS
            )
        }
    }

    private fun cargarContactos() {

        listaContactos.clear()

        val columnas =
            arrayOf(
                ContactsContract
                    .CommonDataKinds
                    .Phone
                    .DISPLAY_NAME,

                ContactsContract
                    .CommonDataKinds
                    .Phone
                    .NUMBER
            )

        val cursor =
            contentResolver.query(
                ContactsContract
                    .CommonDataKinds
                    .Phone
                    .CONTENT_URI,

                columnas,

                null,

                null,

                ContactsContract
                    .CommonDataKinds
                    .Phone
                    .DISPLAY_NAME +
                        " ASC"
            )

        cursor?.use {

            val indiceNombre =
                it.getColumnIndexOrThrow(
                    ContactsContract
                        .CommonDataKinds
                        .Phone
                        .DISPLAY_NAME
                )

            val indiceTelefono =
                it.getColumnIndexOrThrow(
                    ContactsContract
                        .CommonDataKinds
                        .Phone
                        .NUMBER
                )

            while (
                it.moveToNext()
            ) {

                val nombre =
                    it.getString(
                        indiceNombre
                    )

                val telefono =
                    it.getString(
                        indiceTelefono
                    )

                listaContactos.add(
                    Contacto(
                        nombre,
                        telefono
                    )
                )
            }
        }

        adapter.notifyDataSetChanged()
    }
}
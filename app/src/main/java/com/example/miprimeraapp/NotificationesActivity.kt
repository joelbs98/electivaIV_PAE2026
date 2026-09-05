package com.example.miprimeraapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class NotificationesActivity : AppCompatActivity() {
    companion object {
        const val CANAL_ID = "canal_recordatorios"
        const val NOTIFICACION_ID = 1001
    }

    private fun crearCanalNotificacion() {

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O
        ) {

            val nombre =
                getString(
                    R.string.notif_canal_nombre
                )

            val descripcion =
                getString(
                    R.string.notif_canal_descripcion
                )

            val importancia =
                NotificationManager
                    .IMPORTANCE_DEFAULT

            val canal =
                NotificationChannel(
                    CANAL_ID,
                    nombre,
                    importancia
                )

            canal.description =
                descripcion

            val manager =
                getSystemService(
                    NotificationManager::class.java
                )

            manager.createNotificationChannel(
                canal
            )
        }
    }
    private val solicitarPermisoNotificacion = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { concedido ->
        if (!concedido) {
            Toast.makeText(this, R.string.notif_permiso_denegado, Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notificationes)
        crearCanalNotificacion()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etTitulo = findViewById<EditText>(R.id.etTituloNotificacion)
        val etMensaje = findViewById<EditText>(R.id.etMensajeNotificacion)
        val btnEnviar = findViewById<Button>(R.id.btnEnviarNotificacion)
        val tvEstado = findViewById<TextView>(R.id.tvEstadoNotificacion)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED
            ){
                solicitarPermisoNotificacion.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        btnEnviar.setOnClickListener {
            val titulo = etTitulo.text.toString().trim()
            val mensaje = etMensaje.text.toString().trim()
            if (titulo.isEmpty()) {
                etTitulo.error = "Ingrese un titulo"
                return@setOnClickListener
            }
            if (mensaje.isEmpty()) {
                etMensaje.error = "Ingrese un mensaje"
                return@setOnClickListener
            }
            val intent = Intent(this, NotificationesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            val pendingIntent = PendingIntent.getActivity(this,0,intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            val notificacion = NotificationCompat.Builder(this,CANAL_ID)
                .setSmallIcon(R.drawable.ic_notification).setContentTitle(titulo)
                .setContentText(mensaje).setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent).setAutoCancel(true).build()
            if (
                Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.TIRAMISU &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                solicitarPermisoNotificacion.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )

                return@setOnClickListener
            }
            NotificationManagerCompat.from(this).notify(NOTIFICACION_ID,notificacion)
            tvEstado.text = "Notificacion enviada"
        }

    }
}
package com.example.miprimeraapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ContadorService : Service() {
    companion object{
        const val TAG = "CONTADOR_SERVICE"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate: Servicio creado")
    }

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(serviceJob + Dispatchers.Default)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand; Servicio iniciado")
        serviceScope.launch {
            var contador = 1
            while(isActive){
                Log.d(TAG,"Servicio ejecutandose: $contador")
                contador++
                delay(1000)
            }
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG,"onDestroy: Servicio detenido")
        serviceScope.cancel()
        super.onDestroy()
    }


    //No se utilizara el onBind
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}
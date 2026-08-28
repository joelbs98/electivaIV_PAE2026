package com.example.miprimeraapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatosHelper(context: Context) : SQLiteOpenHelper(context, NOMBRE_BD, null, VERSION_BD) {

    companion object {
        const val NOMBRE_BD = "universidad.db"
        const val VERSION_BD = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val crearTabla = """
           CREATE TABLE estudiantes (
           id INTEGER PRIMARY KEY AUTOINCREMENT,
           nombre TEXT NOT NULL,
           carrera TEXT NOT NULL
           )
           """.trimIndent()
        db.execSQL(crearTabla)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS estudiantes")
        onCreate(db)
    }
    fun InsertarEstudiante(nombre: String, carrera: String): Long{
        val db = writableDatabase
        val valores = ContentValues()
        valores.put("nombre",nombre)
        valores.put("carrera",carrera)
        return db.insert("estudiantes",null,valores)
    }
    fun obtenerEstudiantes(): String {
        val db = readableDatabase
        val cursor = db.rawQuery(
            """SELECT id, nombre, carrera FROM estudiantes ORDER BY id DESC""".trimIndent(),null
        )
        val resultado = StringBuilder()
        while (
            cursor.moveToNext()
        ){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
            val carrera = cursor.getString(cursor.getColumnIndexOrThrow("carrera"))
            resultado.append("$id - $nombre - $carrera\n")
        }
        cursor.close()
        return if(resultado.isEmpty()){
            "No existen registros"
        }else{
            resultado.toString()
        }
    }
    fun actualizarEstudiante(
        id: Int,
        nombre: String,
        carrera: String
    ): Int {

        val db =
            writableDatabase

        val valores =
            ContentValues()

        valores.put(
            "nombre",
            nombre
        )

        valores.put(
            "carrera",
            carrera
        )

        return db.update(
            "estudiantes",
            valores,
            "id = ?",
            arrayOf(
                id.toString()
            )
        )
    }
    fun eliminarEstudiante(
        id: Int
    ): Int {

        val db =
            writableDatabase

        return db.delete(
            "estudiantes",
            "id = ?",
            arrayOf(
                id.toString()
            )
        )
    }
}
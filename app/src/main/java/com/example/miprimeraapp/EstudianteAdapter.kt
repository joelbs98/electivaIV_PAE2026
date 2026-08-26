package com.example.miprimeraapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class EstudianteAdapter (private val listaEstudiantes:List<Estudiante>):
    RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder>(){

    class EstudianteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombreEstudiante)
        val tvCarrera: TextView = itemView.findViewById(R.id.tvCarreraEstudiante)
        val tvSemestre: TextView = itemView.findViewById(R.id.tvSemestreEstudiante)

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EstudianteViewHolder {
       val vista = LayoutInflater.from(parent.context).inflate(
           R.layout.item_estudiante,parent,false)
        return EstudianteViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: EstudianteViewHolder,
        position: Int
    ) {
        val estudiante = listaEstudiantes[position]
        holder.tvNombre.text = estudiante.nombre
        holder.tvCarrera.text = estudiante.carrera
        holder.tvSemestre.text = "Semestre: ${estudiante.semestre}"
    }

    override fun getItemCount(): Int {
      return listaEstudiantes.size
    }

}
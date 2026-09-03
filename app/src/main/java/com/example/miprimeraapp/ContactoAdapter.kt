import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimeraapp.Contacto
import com.example.miprimeraapp.R

class ContactoAdapter(
    private val contactos: List<Contacto>
) : RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder>() {

    class ContactoViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val tvNombre: TextView =
            itemView.findViewById(
                R.id.tvNombreContacto
            )

        val tvTelefono: TextView =
            itemView.findViewById(
                R.id.tvTelefonoContacto
            )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactoViewHolder {

        val vista =
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_contacto,
                    parent,
                    false
                )

        return ContactoViewHolder(
            vista
        )
    }

    override fun onBindViewHolder(
        holder: ContactoViewHolder,
        position: Int
    ) {

        val contacto =
            contactos[position]

        holder.tvNombre.text =
            contacto.nombre

        holder.tvTelefono.text =
            contacto.telefono
    }

    override fun getItemCount(): Int {

        return contactos.size
    }
}
package sv.edu.udb.libapi

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class RecursoAdapter(private val recursos: List<Recurso>) :
    RecyclerView.Adapter<RecursoAdapter.ViewHolder> (){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tituloTextView: TextView = view.findViewById(R.id.tvTitulo)
        val descripcionTextView: TextView = view.findViewById(R.id.tvDescripcion)
        val tipoTextView: TextView = view.findViewById(R.id.tvTipo)
        val imagenTextView: TextView = view.findViewById(R.id.tvImagen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recurso_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recurso = recursos[position]
        holder.tituloTextView.text = recurso.titulo
        holder.descripcionTextView.text = recurso.descripcion
        holder.tipoTextView.text = recurso.tipo
        holder.imagenTextView.text = recurso.imagen
    }

    override fun getItemCount(): Int {
        return recursos.size
    }

}
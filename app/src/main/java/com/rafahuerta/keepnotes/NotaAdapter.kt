package com.rafahuerta.keepnotes

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView

    class NotaAdapter(
        private var notas: List<Nota>,
        private val onNotaClick: (Nota) -> Unit,
        private val onNotaLongClick: (Nota) -> Unit
    ) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

        inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
            val tvContenido: TextView = itemView.findViewById(R.id.tvContenido)
            val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
            val vista = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_nota, parent, false)
            return NotaViewHolder(vista)
        }

        override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
            val nota = notas[position]
            holder.tvTitulo.text = nota.titulo
            holder.tvContenido.text = nota.contenido
            holder.tvFecha.text = nota.fecha
            holder.itemView.setOnClickListener { onNotaClick(nota) }
            holder.itemView.setOnLongClickListener {
                onNotaLongClick(nota)
                true
            }
        }

        override fun getItemCount(): Int = notas.size

        fun actualizarNotas(nuevasNotas: List<Nota>) {
            notas = nuevasNotas
            notifyDataSetChanged()
        }
    }
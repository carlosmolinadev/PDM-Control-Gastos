package com.carlos.ahorromatic.ui.historial


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlos.ahorromatic.R
import com.carlos.ahorromatic.db.entities.GastoEntity


class HistorialGastosAdapter(onDeleteClickListener: OnDeleteClickListener) : ListAdapter<GastoEntity, HistorialGastosAdapter.MyViewHolder>(GastoComparator()) {

    private val mOnDeleteClickListener = onDeleteClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent, mOnDeleteClickListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class MyViewHolder(itemView: View, onDeleteClickListener: OnDeleteClickListener) : RecyclerView.ViewHolder(itemView) {
        private val onDeleteClickListener = onDeleteClickListener
        private val categoria:TextView = itemView.findViewById(R.id.transaction_item_category)
        private val monto: TextView = itemView.findViewById(R.id.transaction_item_monto)
        private val dia: TextView = itemView.findViewById(R.id.transaction_item_dia)
        private val mes: TextView = itemView.findViewById(R.id.transaction_item_mes)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)

        fun bind(gastos: GastoEntity) {
            categoria.text = gastos.categoria
            monto.text = gastos.monto.toString()
            mes.text = getMonthName(gastos.mes)
            dia.text = gastos.dia.toString()

            deleteButton.setOnClickListener {
                onDeleteClickListener.onDeleteGasto(gastos)
            }
        }

        companion object {
            fun create(parent: ViewGroup, onDeleteClickListener: OnDeleteClickListener):
                    MyViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recycler_transaction_item, parent, false)
                return MyViewHolder(view, onDeleteClickListener)
            }
        }

        private fun getMonthName(position:Int): String{
            val lista:List<String> = listOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
            val mes = lista[position-1]
            return mes
        }


    }

    class GastoComparator : DiffUtil.ItemCallback<GastoEntity>() {
        override fun areItemsTheSame(oldItem: GastoEntity, newItem: GastoEntity): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: GastoEntity, newItem: GastoEntity):
                Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    interface OnDeleteClickListener {
        fun onDeleteGasto(gasto: GastoEntity)
    }
}
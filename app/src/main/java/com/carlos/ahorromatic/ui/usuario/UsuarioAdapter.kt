package com.carlos.ahorromatic.ui.usuario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlos.ahorromatic.R
import com.carlos.ahorromatic.db.entities.UsuarioEntity

class UsuarioAdapter(adapterClickListener: AdapterClickListeners) : ListAdapter<UsuarioEntity, UsuarioAdapter.MyViewHolder>(UserComparator()) {

    private val mAdapterClickListener = adapterClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent, mAdapterClickListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class MyViewHolder(itemView: View, adapterClickListener: AdapterClickListeners) : RecyclerView.ViewHolder(itemView) {
        private val adapterClickListener = adapterClickListener
        private val usuarioField: TextView = itemView.findViewById(R.id.usuario_item_name)
        private val selectButton: ImageButton = itemView.findViewById(R.id.usuario_select_button)
        private val updateButton: ImageButton = itemView.findViewById(R.id.usuario_edit_button)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.usuario_delete_button)

        fun bind(usuario: UsuarioEntity) {
            System.out.println(usuario)
            usuarioField.text = "${usuario.apellido} ${usuario.nombre}"
            selectButton.setOnClickListener {
                adapterClickListener.onSelectUser(usuario)
            }

            updateButton.setOnClickListener {
                adapterClickListener.onEditUser(usuario)
            }
            
            deleteButton.setOnClickListener {
                adapterClickListener.onDeleteUser(usuario)
            }
        }

        companion object {
            fun create(parent: ViewGroup, AdapterClickListeners: AdapterClickListeners):
                    MyViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_usuarios_item, parent, false)
                return MyViewHolder(view, AdapterClickListeners)
            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<UsuarioEntity>() {
        override fun areItemsTheSame(oldItem: UsuarioEntity, newItem: UsuarioEntity): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: UsuarioEntity, newItem: UsuarioEntity):
                Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    interface AdapterClickListeners {
        fun onSelectUser(usuario: UsuarioEntity)
        fun onEditUser(usuario: UsuarioEntity)
        fun onDeleteUser(usuario: UsuarioEntity)
    }
}
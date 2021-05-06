package com.carlos.ahorromatic.ui.usuario

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlos.ahorromatic.R
import com.carlos.ahorromatic.RegistroApplication
import com.carlos.ahorromatic.db.entities.UsuarioEntity
import com.carlos.ahorromatic.RegistroApplication.Companion


class UsuarioFragment : Fragment(), UsuarioAdapter.AdapterClickListeners {

    companion object {
        fun newInstance() = UsuarioFragment()
    }

    private lateinit var viewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as RegistroApplication

        viewModel = ViewModelProvider(requireActivity(),
            UsuarioViewModelFactory(application.repository)).get(UsuarioViewModel::class.java)
        return inflater.inflate(R.layout.usuario_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuarios = viewModel.usuarios


        //Recycler View
        val adapter = UsuarioAdapter(this)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.usuarios_recyclerview)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)

        // Databinding
        viewModel.usuarios.observe(viewLifecycleOwner, Observer { usuarios ->
            usuarios?.let { adapter.submitList(it) }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onSelectUser(usuario: UsuarioEntity) {
        val currentUser = activity?.application as RegistroApplication
        currentUser.setCurrentUser(usuario)
        Toast.makeText(requireContext(), "Perfil del usuario ${usuario.nombre} ha sido cargado exitosamente!", Toast.LENGTH_LONG).show()
    }

    override fun onEditUser(usuario: UsuarioEntity) {
        viewModel.usuarioActual = usuario
        findNavController().navigate(R.id.action_nav_usuario_to_nav_guardar_usuario)
    }

    override fun onDeleteUser(usuario: UsuarioEntity) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Estas seguro que deseas borrar el usuario ${usuario.apellido} ${usuario.nombre}?").setCancelable(false).setPositiveButton("Si")
        { dialog, id ->
            viewModel.delete(usuario)
        }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

}
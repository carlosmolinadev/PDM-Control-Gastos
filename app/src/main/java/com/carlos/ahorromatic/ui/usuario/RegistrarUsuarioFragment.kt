package com.carlos.ahorromatic.ui.usuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.carlos.ahorromatic.R
import com.carlos.ahorromatic.RegistroApplication
import com.carlos.ahorromatic.db.entities.UsuarioEntity

class RegistrarUsuarioFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuarios = viewModel.usuarios
        val currentUser = activity?.application as RegistroApplication
        currentUser.setCurrentUser(UsuarioEntity(7, "Carlos", "Molina"))

    }

}
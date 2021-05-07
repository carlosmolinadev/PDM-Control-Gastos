package com.carlos.ahorromatic.ui.usuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        return inflater.inflate(R.layout.fragment_guardar_usuario, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val currentUser = viewModel.usuarioActual

        val nameField = view?.findViewById<EditText>(R.id.editTextTextPersonName)
        val lastNameField = view?.findViewById<EditText>(R.id.editTextTextPersonNameLastName)
        val ahorroField = view?.findViewById<EditText>(R.id.editTextNumberDecimal)
        val registerButton = view?.findViewById<Button>(R.id.usuario_guardar_registro_btn)

        if(currentUser != null){
            nameField.setText(currentUser.nombre)
            lastNameField.setText(currentUser.apellido)
            ahorroField.setText(currentUser.ahorro.toString())
        }

        registerButton.setOnClickListener {
            if(nameField.text.isNullOrEmpty() || lastNameField.text.isNullOrEmpty() || ahorroField.text.isNullOrEmpty()){
                Toast.makeText(context, "Todos los campos son requeridos",
                        Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(currentUser != null){
                viewModel.update(UsuarioEntity(currentUser.id, nameField.text.toString(), lastNameField.text.toString(), ahorroField.text.toString().toDouble()))
            } else {
                viewModel.insert(UsuarioEntity(0, nameField.text.toString(), lastNameField.text.toString(), ahorroField.text.toString().toDouble()))
            }

            findNavController().navigateUp()
        }

    }

}
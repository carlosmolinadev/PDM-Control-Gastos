package com.carlos.ahorromatic.ui.inicio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.carlos.ahorromatic.R

class InicioFragment : Fragment() {

    companion object {
        fun newInstance() = InicioFragment()
    }

    private lateinit var viewModel: InicioViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.inicio_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InicioViewModel::class.java)
        val ingresoButtonRedirect = view?.findViewById<ImageButton>(R.id.ingreso_btn)
        ingresoButtonRedirect?.setOnClickListener {
            findNavController().navigate(R.id.action_nav_inicio_to_nav_ingresos)
        }

        val gastosButtonRedirect = view?.findViewById<ImageButton>(R.id.gastos_btn)
        gastosButtonRedirect?.setOnClickListener {
            findNavController().navigate(R.id.action_nav_inicio_to_nav_gastos)
        }

        val historialButtonRedirect = view?.findViewById<ImageButton>(R.id.historial_btn)
        historialButtonRedirect?.setOnClickListener {
            findNavController().navigate(R.id.action_nav_inicio_to_nav_historial)
        }

    }

}
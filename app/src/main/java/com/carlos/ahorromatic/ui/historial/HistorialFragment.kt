package com.carlos.ahorromatic.ui.historial

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlos.ahorromatic.R
import com.carlos.ahorromatic.RegistroApplication
import com.carlos.ahorromatic.db.entities.GastoEntity
import com.carlos.ahorromatic.db.entities.IngresoEntity

class HistorialFragment : Fragment() {
    companion object {
        fun newInstance() = HistorialFragment()
    }

    private lateinit var viewModel: HistorialViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as RegistroApplication
        viewModel = ViewModelProvider(requireActivity(),
                HistorialViewModelFactory(application.repository)).get(HistorialViewModel::class.java)
        return inflater.inflate(R.layout.historial_fragment, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historialButtonRedirect = view?.findViewById<Button>(R.id.historial_to_ingresos_btn)
        historialButtonRedirect?.setOnClickListener {
            findNavController().navigate(R.id.action_nav_historial_to_nav_historial_ingresos)
        }

        val historialGastos = view?.findViewById<Button>(R.id.historial_to_gastos_btn)
        historialGastos?.setOnClickListener {
            findNavController().navigate(R.id.action_nav_historial_to_nav_historial_gastos)
        }
        
    }


}
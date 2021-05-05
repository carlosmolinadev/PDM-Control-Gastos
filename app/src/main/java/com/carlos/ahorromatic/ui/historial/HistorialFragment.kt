package com.carlos.ahorromatic.ui.historial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.carlos.ahorromatic.R
import com.carlos.ahorromatic.RegistroApplication
import org.w3c.dom.Text

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
        viewModel = ViewModelProvider(
            requireActivity(),
            HistorialViewModelFactory(application.repository)
        ).get(HistorialViewModel::class.java)
        return inflater.inflate(R.layout.historial_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mes = 1
        var gastosMensuales:Double? = null
        var ingresosMensuales:Double = 0.0
        val mostrarAhorroAcumulado = view.findViewById<TextView>(R.id.historial_total_acumulado_resultado)
        val mostrarIngresoMensual = view.findViewById<TextView>(R.id.historial_ingresos_mensuales)
        val mostrarGastoMensual = view.findViewById<TextView>(R.id.historial_gastos_mensuales)
        viewModel.getTotalIngresosByUser(1, 1).observe(viewLifecycleOwner, { ingresos -> mostrarIngresoMensual.text = ingresos?.toString()})
        viewModel.getTotalGastosByUser(1, 1).observe(viewLifecycleOwner, { gastos -> gastos?.let { mostrarGastoMensual.text = it.toString() } })

        val testButton = view.findViewById<Button>(R.id.button_test)

        val seleccionarMesSpinner: Spinner = requireView().findViewById(R.id.seleccionar_mes_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.meses_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            seleccionarMesSpinner.adapter = adapter
        }
        seleccionarMesSpinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mes = (position + 1)
                viewModel.getTotalIngresosByUser(1, mes).observe(viewLifecycleOwner, { ingresos ->
                    if(ingresos == null){
                        mostrarIngresoMensual.text = "0.00"
                    } else {
                        mostrarIngresoMensual.text = ingresos?.toString()
                    }

                })

                viewModel.getTotalGastosByUser(1, mes).observe(viewLifecycleOwner, { gastos ->
                    if(gastos == null){
                        mostrarGastoMensual.text = "0.00"
                    } else {
                        mostrarGastoMensual.text = gastos?.toString()
                    }

                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        testButton.setOnClickListener {
            viewModel.getTotalIngresosByUser(1, 4).observe(viewLifecycleOwner, { ingresos -> mostrarIngresoMensual.text = ingresos?.toString()})
        }

        //mostrarAhorroAcumulado.text = it.toString()
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
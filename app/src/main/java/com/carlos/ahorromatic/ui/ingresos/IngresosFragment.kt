package com.carlos.ahorromatic.ui.ingresos

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.carlos.ahorromatic.R
import com.carlos.ahorromatic.RegistroApplication
import com.carlos.ahorromatic.db.entities.IngresoEntity

class IngresosFragment : Fragment() {

    companion object {
        fun newInstance() = IngresosFragment()
    }

    private lateinit var viewModel: IngresosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as RegistroApplication

        viewModel = ViewModelProvider(requireActivity(),
            IngresoViewModelFactory(application.repository)).get(IngresosViewModel::class.java)

        return inflater.inflate(R.layout.ingresos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val currentUser = sharedPref.getInt("currentUser", 1)

        // Variables that will be added to the database
        var monto:Double? = null
        var categoria:String = ""
        var detalles:String = ""
        var dia:Int = 1
        var mes:Int = 1
        var anio:Int = 2021

        // Elements from view
        val registrarButton: Button = requireView().findViewById(R.id.ingresar_registro_btn)
        val montoEditText:EditText = requireView().findViewById(R.id.ingresar_monto_editText)
        val detallesEditText:EditText = requireView().findViewById(R.id.ingresos_detalles_editTextMulti)
        val diaEditText:EditText = requireView().findViewById(R.id.ingresos_dia_editText)

        val spinnerCategoria: Spinner = requireView().findViewById(R.id.ingresos_categoria_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.ingresos_categoria_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerCategoria.adapter = adapter
        }
        spinnerCategoria.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val ingresoListaCategoria = resources.getStringArray(R.array.ingresos_categoria_array)
                categoria = ingresoListaCategoria[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val spinnerMes: Spinner = requireView().findViewById(R.id.ingresos_mes_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.meses_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerMes.adapter = adapter
        }
        spinnerMes.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mes = (position + 1)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val spinnerAnio: Spinner = requireView().findViewById(R.id.ingresos_anio_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.anios_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerAnio.adapter = adapter
        }
        spinnerAnio.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val ingresoListaAnio = resources.getStringArray(R.array.anios_array)
                anio = ingresoListaAnio[position].toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        registrarButton.setOnClickListener {
            if(montoEditText.text.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Favor ingresar monto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(diaEditText.text.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Favor ingresar el dia", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            monto = montoEditText.text.toString().toDouble()
            detalles = detallesEditText.text.toString()
            dia = diaEditText.text.toString().toInt()

            viewModel.insert(IngresoEntity(
                0, monto!!, categoria, dia, mes, anio, detalles, currentUser
            ))
            Toast.makeText(requireContext(), "El registro ha sido ingresado exitosamente!", Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
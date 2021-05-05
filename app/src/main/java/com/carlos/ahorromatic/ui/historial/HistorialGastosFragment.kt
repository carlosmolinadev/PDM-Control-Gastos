package com.carlos.ahorromatic.ui.historial

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlos.ahorromatic.R
import com.carlos.ahorromatic.RegistroApplication
import com.carlos.ahorromatic.db.entities.GastoEntity
import com.carlos.ahorromatic.db.entities.IngresoEntity

class HistorialGastosFragment : Fragment(), HistorialGastosAdapter.OnDeleteClickListener  {

    companion object {
        fun newInstance() = HistorialGastosFragment()
    }

    private lateinit var viewModel: HistorialViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as RegistroApplication
        viewModel = ViewModelProvider(requireActivity(),
                HistorialViewModelFactory(application.repository)).get(HistorialViewModel::class.java)
        return inflater.inflate(R.layout.fragment_historial_gastos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recycler View
        val adapter = HistorialGastosAdapter(this)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.gastos_recyclerview)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)

        // HistoryModel
        viewModel.gastos.observe(viewLifecycleOwner, Observer { gastos ->
            gastos?.let { adapter.submitList(it) }
        })

    }

    override fun onDeleteGasto(gasto: GastoEntity) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Estas seguro que deseas borrar el registro?").setCancelable(false).setPositiveButton("Si")
        { dialog, id ->
            viewModel.deleteGasto(gasto)
        }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }
        val alert = builder.create()
        alert.show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
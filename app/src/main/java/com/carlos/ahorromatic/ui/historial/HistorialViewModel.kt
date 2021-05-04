package com.carlos.ahorromatic.ui.historial

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.carlos.ahorromatic.db.entities.GastoEntity
import com.carlos.ahorromatic.db.entities.IngresoEntity
import com.carlos.ahorromatic.db.entities.RegistroRepository
import kotlinx.coroutines.launch

class HistorialViewModel(private val repository: RegistroRepository) : ViewModel() {
    val ingresos: LiveData<List<IngresoEntity>> = repository.ingresos
    val gastos: LiveData<List<GastoEntity>> = repository.gastos
    fun deleteIngreso(ingreso: IngresoEntity) = viewModelScope.launch {
        repository.delete(ingreso)
    }
    fun deleteGasto(gasto: GastoEntity) = viewModelScope.launch {
        repository.delete(gasto)
    }
}

class HistorialViewModelFactory(private val repository: RegistroRepository) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistorialViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistorialViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
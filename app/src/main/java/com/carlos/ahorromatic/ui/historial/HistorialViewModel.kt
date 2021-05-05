package com.carlos.ahorromatic.ui.historial

import androidx.lifecycle.*
import com.carlos.ahorromatic.db.entities.GastoEntity
import com.carlos.ahorromatic.db.entities.IngresoEntity
import com.carlos.ahorromatic.db.entities.RegistroRepository
import kotlinx.coroutines.launch

class HistorialViewModel(private val repository: RegistroRepository) : ViewModel() {
    val ingresos: LiveData<List<IngresoEntity>> = repository.ingresos
    val gastos: LiveData<List<GastoEntity>> = repository.gastos

    fun getTotalIngresosByUser(id:Int, mes:Int):LiveData<Double>{
        return repository.getTotalIngresosByUser(id, mes)
    }

    fun getTotalGastosByUser(id:Int, mes:Int):LiveData<Double>{
        return repository.getTotalGastosByUser(id, mes)
    }

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
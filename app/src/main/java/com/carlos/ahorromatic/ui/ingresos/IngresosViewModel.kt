package com.carlos.ahorromatic.ui.ingresos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.carlos.ahorromatic.db.entities.IngresoEntity
import com.carlos.ahorromatic.db.entities.RegistroRepository
import kotlinx.coroutines.launch

class IngresosViewModel(private val repository: RegistroRepository) : ViewModel() {
    val ingresos: LiveData<List<IngresoEntity>> = repository.ingresos
    fun insert(ingreso: IngresoEntity) = viewModelScope.launch {
        repository.insert(ingreso)
    }
    fun update(ingreso: IngresoEntity) = viewModelScope.launch {
        repository.update(ingreso)
    }
    fun delete(ingreso: IngresoEntity) = viewModelScope.launch {
        repository.delete(ingreso)
    }
}

class IngresoViewModelFactory(private val repository: RegistroRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngresosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IngresosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
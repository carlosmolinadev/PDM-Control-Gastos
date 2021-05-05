package com.carlos.ahorromatic.ui.gastos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.carlos.ahorromatic.db.entities.GastoEntity
import com.carlos.ahorromatic.db.entities.RegistroRepository
import kotlinx.coroutines.launch

class GastosViewModel(private val repository: RegistroRepository) : ViewModel() {
    val gastos: LiveData<List<GastoEntity>> = repository.gastos

    val total: LiveData<Double> = repository.total

    fun insert(gasto: GastoEntity) = viewModelScope.launch {
        repository.insert(gasto)
    }
    fun update(gasto: GastoEntity) = viewModelScope.launch {
        repository.update(gasto)
    }
    fun delete(gasto: GastoEntity) = viewModelScope.launch {
        repository.delete(gasto)
    }
    fun getExpenseById(id:Int) = viewModelScope.launch {
        repository.getExpenseById(id)
    }
}

class GastoViewModelFactory(private val repository: RegistroRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GastosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GastosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
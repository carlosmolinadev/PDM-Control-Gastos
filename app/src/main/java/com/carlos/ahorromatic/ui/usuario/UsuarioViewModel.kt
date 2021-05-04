package com.carlos.ahorromatic.ui.usuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.carlos.ahorromatic.db.entities.RegistroRepository
import com.carlos.ahorromatic.db.entities.UsuarioEntity
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: RegistroRepository) : ViewModel() {
    val usuarios: LiveData<List<UsuarioEntity>> = repository.usuarios
    var usuarioActual: UsuarioEntity? = null
    fun insert(usuario: UsuarioEntity) = viewModelScope.launch {
        repository.insert(usuario)
    }
    fun update(usuario: UsuarioEntity) = viewModelScope.launch {
        repository.update(usuario)
    }
    fun delete(usuario: UsuarioEntity) = viewModelScope.launch {
        repository.delete(usuario)
    }
}

class UsuarioViewModelFactory(private val repository: RegistroRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsuarioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
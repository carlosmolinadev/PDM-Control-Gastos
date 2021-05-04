package com.carlos.ahorromatic

import android.app.Application
import com.carlos.ahorromatic.db.entities.RegistroDB
import com.carlos.ahorromatic.db.entities.RegistroRepository
import com.carlos.ahorromatic.db.entities.UsuarioEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RegistroApplication: Application() {
    companion object {
        var currentUser:UsuarioEntity? = null
    }

    fun setCurrentUser(user:UsuarioEntity){
        currentUser = user
    }

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { RegistroDB.getDatabase(this, applicationScope) }
    val repository by lazy { RegistroRepository(database) }
}
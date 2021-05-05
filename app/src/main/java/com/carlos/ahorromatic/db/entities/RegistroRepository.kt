package com.carlos.ahorromatic.db.entities

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class RegistroRepository(private val db: RegistroDB) {
    /***************************
     * Usuario repository
     ***************************/
    val usuarios: LiveData<List<UsuarioEntity>> = db.usuarioDao().getAll()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(usuario: UsuarioEntity) {
        db.usuarioDao().insert(usuario)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(usuario: UsuarioEntity) {
        db.usuarioDao().update(usuario)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(usuario: UsuarioEntity) {
        db.usuarioDao().delete(usuario)
    }

    /***************************
     * Gasto repository
     ***************************/
    val gastos: LiveData<List<GastoEntity>> = db.gastoDao().getAll()

    fun getTotalGastosByUser (id:Int, mes:Int): LiveData<Double>{
        val totalGastos: LiveData<Double> = db.gastoDao().getExpenseTotal(id, mes)
        return totalGastos
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(gasto: GastoEntity) {
        db.gastoDao().insert(gasto)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(gasto: GastoEntity) {
        db.gastoDao().update(gasto)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(gasto: GastoEntity) {
        db.gastoDao().delete(gasto)
    }

    /***************************
     * Ingreso repository
     ***************************/
    val ingresos: LiveData<List<IngresoEntity>> = db.ingresoDao().getAll()

    fun getTotalIngresosByUser (id:Int, mes:Int): LiveData<Double>{
        val totalIngresos: LiveData<Double> = db.ingresoDao().getExpenseTotal(id, mes)
        return totalIngresos
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(ingreso: IngresoEntity) {
        db.ingresoDao().insert(ingreso)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(ingreso: IngresoEntity) {
        db.ingresoDao().update(ingreso)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(ingreso: IngresoEntity) {
        db.ingresoDao().delete(ingreso)
    }

    /***************************
     * Logro repository
     ***************************/
    val logros: LiveData<List<LogroEntity>> = db.logroDao().getAll()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(logro: LogroEntity) {
        db.logroDao().insert(logro)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(logro: LogroEntity) {
        db.logroDao().update(logro)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(logro: LogroEntity) {
        db.logroDao().delete(logro)
    }
}
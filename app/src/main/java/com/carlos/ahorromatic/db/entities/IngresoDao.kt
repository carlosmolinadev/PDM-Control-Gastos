package com.carlos.ahorromatic.db.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IngresoDao {
    @Query("SELECT * FROM ingreso")
    fun getAll(): LiveData<List<IngresoEntity>>

    @Query("SELECT * FROM ingreso WHERE usuario_id = :id")
    fun getExpenseByUserId(id:Int): LiveData<List<GastoEntity>>

    @Query("SELECT SUM(monto) FROM ingreso WHERE usuario_id = :id AND mes = :mes")
    fun getExpenseTotal(id:Int, mes:Int): LiveData<Double>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ingreso: IngresoEntity)

    @Update
    suspend fun update(ingreso: IngresoEntity)

    @Delete
    suspend fun delete(ingreso: IngresoEntity)

    @Query("DELETE FROM ingreso")
    suspend fun deleteAll()
}
package com.carlos.ahorromatic.db.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GastoDao {
    @Query("SELECT * FROM gasto")
    fun getAll(): LiveData<List<GastoEntity>>

    @Query("SELECT * FROM gasto WHERE usuario_id = :id AND mes = :mes")
    fun getExpenseByUserId(id:Int, mes:Int): LiveData<List<GastoEntity>>

    @Query("SELECT SUM(monto) FROM gasto WHERE usuario_id = :id AND mes = :mes")
    fun getExpenseTotal(id:Int, mes:Int): LiveData<Double>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(gasto: GastoEntity)
    @Update
    suspend fun update(gasto: GastoEntity)
    @Delete
    suspend fun delete(gasto: GastoEntity)
    @Query("DELETE FROM gasto")
    suspend fun deleteAll()
}
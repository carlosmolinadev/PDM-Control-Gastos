package com.carlos.ahorromatic.db.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IngresoDao {
    @Query("SELECT * FROM ingreso")
    fun getAll(): LiveData<List<IngresoEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ingreso: IngresoEntity)
    @Update
    suspend fun update(ingreso: IngresoEntity)
    @Delete
    suspend fun delete(ingreso: IngresoEntity)
    @Query("DELETE FROM ingreso")
    suspend fun deleteAll()
}
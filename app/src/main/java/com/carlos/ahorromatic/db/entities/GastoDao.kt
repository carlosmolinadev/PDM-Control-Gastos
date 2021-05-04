package com.carlos.ahorromatic.db.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GastoDao {
    @Query("SELECT * FROM gasto")
    fun getAll(): LiveData<List<GastoEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(gasto: GastoEntity)
    @Update
    suspend fun update(gasto: GastoEntity)
    @Delete
    suspend fun delete(gasto: GastoEntity)
    @Query("DELETE FROM gasto")
    suspend fun deleteAll()
}
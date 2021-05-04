package com.carlos.ahorromatic.db.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LogroDao {
    @Query("SELECT * FROM logro")
    fun getAll(): LiveData<List<LogroEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(logro: LogroEntity)
    @Update
    suspend fun update(logro: LogroEntity)
    @Delete
    suspend fun delete(logro: LogroEntity)
    @Query("DELETE FROM logro")
    suspend fun deleteAll()
}
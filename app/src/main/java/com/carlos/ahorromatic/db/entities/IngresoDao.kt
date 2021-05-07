package com.carlos.ahorromatic.db.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IngresoDao {
    @Query("SELECT * FROM ingreso")
    fun getAll(): LiveData<List<IngresoEntity>>

    @Query("SELECT * FROM ingreso WHERE usuario_id = :id AND mes = :mes")
    fun getIngresosListByUserId(id:Int, mes:Int): LiveData<List<IngresoEntity>>

    @Query("SELECT SUM(monto) FROM ingreso WHERE usuario_id = :id AND mes = :mes")
    fun getExpenseTotal(id:Int, mes:Int): LiveData<Double>

    @Query(
            "SELECT SUM((SELECT SUM(ingreso.monto) FROM ingreso JOIN usuario on usuario.id = ingreso.usuario_id WHERE usuario_id = :id) - (SELECT SUM(gasto.monto) FROM gasto JOIN usuario on usuario.id = gasto.usuario_id WHERE usuario_id = :id)) FROM usuario WHERE id = :id;"
    )

    fun getAhorroAcumulado(id:Int): LiveData<Double>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ingreso: IngresoEntity)

    @Update
    suspend fun update(ingreso: IngresoEntity)

    @Delete
    suspend fun delete(ingreso: IngresoEntity)

    @Query("DELETE FROM ingreso")
    suspend fun deleteAll()
}
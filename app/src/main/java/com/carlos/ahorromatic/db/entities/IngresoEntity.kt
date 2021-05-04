package com.carlos.ahorromatic.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
        tableName = "ingreso",
        foreignKeys = [
            ForeignKey(
                    entity = UsuarioEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["usuario_id"],
                    onDelete = ForeignKey.CASCADE
            )
        ]
)
data class IngresoEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val monto: Double,
        val categoria: String,
        val dia: Int,
        val mes: Int,
        val anio: Int,
        val detalles: String?,
        @ColumnInfo(name = "usuario_id")
        val usuarioId: Int,

)
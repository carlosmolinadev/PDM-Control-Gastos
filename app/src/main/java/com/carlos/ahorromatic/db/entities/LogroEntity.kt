package com.carlos.ahorromatic.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "logro",
    foreignKeys = [
        ForeignKey(
            entity = UsuarioEntity::class,
            parentColumns = ["id"],
            childColumns = ["usuario_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LogroEntity(
        @PrimaryKey
        @ColumnInfo(name = "usuario_id")
        val usuario_id: Int,
        @ColumnInfo(name = "ahorro_mensual")
        val ahorroAcumulado: Double,
)
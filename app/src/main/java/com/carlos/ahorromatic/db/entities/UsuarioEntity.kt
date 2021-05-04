package com.carlos.ahorromatic.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val apellido: String,
)
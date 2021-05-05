package com.carlos.ahorromatic.ui.historial

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TransactionEntity(
        val id: Int,
        val monto: Double,
        val categoria: String,
        val dia: Int,
        val mes: Int,
        val anio: Int,
        val detalles: String?,
        val usuarioId: Int,
)
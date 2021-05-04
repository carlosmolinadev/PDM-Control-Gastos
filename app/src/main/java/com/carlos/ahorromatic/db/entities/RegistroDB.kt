package com.carlos.ahorromatic.db.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*


@Database(
        entities = [
            UsuarioEntity::class,
            GastoEntity::class,
            IngresoEntity::class,
            LogroEntity::class,
        ],
        version = 1,
        exportSchema = false
)

abstract class RegistroDB : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun gastoDao(): GastoDao
    abstract fun ingresoDao(): IngresoDao
    abstract fun logroDao(): LogroDao

    private class DBCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database)
                }
            }
        }

        suspend fun populateDatabase(db: RegistroDB) {
            // Limpiar database
            db.usuarioDao().deleteAll()
            db.gastoDao().deleteAll()
            db.ingresoDao().deleteAll()
            db.logroDao().deleteAll()

            val arrNombre = listOf("Carlos", "Efrain", "David")
            val arrApellido = listOf("Molina", "Flores", "Escobar")
            val arrIngresoCat = listOf("Sueldo", "Ventas")
            val arrIngresoDetalle = listOf("Ingreso por pago de planilla", "Venta de un telefono")
            val arrIngresoMonto = listOf(100.00, 150.00)
            val arrDiaI = listOf(1, 2)
            val arrMesI = listOf(1, 1)
            val arrAnioI = listOf(2021, 2021)
            val arrGastoCat = listOf("Casa", "Viveres")
            val arrGastoDetalle = listOf("Pago de mensualidad", "Compra de carnes y vegetales")
            val arrGastoMonto = listOf(160.00, 170.00)
            val arrDiaG = listOf(3, 4)
            val arrMesG = listOf(1, 1)
            val arrAnioG = listOf(2021, 2021)

            for (i in 0..1) {
                db.usuarioDao().insert(UsuarioEntity(0, arrNombre[i], arrApellido[i]
                ))
            }

            for (i in 0..1) {
                db.ingresoDao().insert(IngresoEntity(
                        0, arrIngresoMonto[i], arrIngresoCat[i], arrDiaI[i], arrMesI[i],
                        arrAnioI[i], arrIngresoDetalle[i], 1
                ))
            }

            for (i in 0..1) {
                db.gastoDao().insert(GastoEntity(
                        0, arrGastoMonto[i], arrGastoCat[i], arrDiaG[i], arrMesG[i],
                         arrAnioG[i], arrGastoDetalle[i], 1
                ))
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: RegistroDB? = null
        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): RegistroDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RegistroDB::class.java,
                        "registro_db"
                ).addCallback(DBCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
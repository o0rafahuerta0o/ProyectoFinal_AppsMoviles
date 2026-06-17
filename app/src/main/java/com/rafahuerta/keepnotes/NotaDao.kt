package com.rafahuerta.keepnotes

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {

    @Query("SELECT * FROM notas ORDER BY id DESC")
    fun obtenerTodasLasNotas(): Flow<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(nota: Nota)

    @Delete
    suspend fun eliminar(nota: Nota)
}
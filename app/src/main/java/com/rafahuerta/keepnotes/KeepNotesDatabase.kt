package com.rafahuerta.keepnotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Nota::class], version = 1)
abstract class KeepNotesDatabase : RoomDatabase() {

    abstract fun notaDao(): NotaDao

    companion object {
        private var instancia: KeepNotesDatabase? = null

        fun getDatabase(context: Context): KeepNotesDatabase {
            if (instancia == null) {
                instancia = Room.databaseBuilder(
                    context,
                    KeepNotesDatabase::class.java,
                    "keepnotes_database"
                ).build()
            }
            return instancia!!
        }
    }
}
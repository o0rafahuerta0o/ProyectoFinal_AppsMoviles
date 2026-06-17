package com.rafahuerta.keepnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: NotaDao
    val todasLasNotas: LiveData<List<Nota>>

    init {
        val database = KeepNotesDatabase.getDatabase(application)
        dao = database.notaDao()
        todasLasNotas = dao.obtenerTodasLasNotas().asLiveData()
    }

    fun guardarNota(nota: Nota) {
        viewModelScope.launch {
            dao.insertar(nota)
        }
    }

    fun eliminarNota(nota: Nota) {
        viewModelScope.launch {
            dao.eliminar(nota)
        }
    }
}
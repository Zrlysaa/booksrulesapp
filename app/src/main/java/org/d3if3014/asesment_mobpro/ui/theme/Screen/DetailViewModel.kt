package org.d3if3014.asesment_mobpro.ui.theme.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3014.asesment_mobpro.database.KritikSaranDao
import org.d3if3014.asesment_mobpro.model.KritikSaran

class DetailViewModel(private val dao: KritikSaranDao) : ViewModel() {
    fun insert(nama: String, tanggal: String, zakat: String, target: String) {
        val kritikSaran = KritikSaran(
            namamu = nama,
            tanggal = tanggal,
            zakat = zakat,
            target = target
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(kritikSaran)
        }
    }
    suspend fun getKritikSaran(id: Long): KritikSaran? {
        return dao.getKritikSaranById(id)
    }

    fun update(id: Long, nama: String, tanggal: String, zakat: String, target: String) {
        val kritikSaran = KritikSaran(
            id = id,
            namamu = nama,
            tanggal = tanggal,
            zakat = zakat,
            target = target
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(kritikSaran)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}
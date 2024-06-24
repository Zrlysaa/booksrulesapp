package org.d3if3014.asesment_mobpro.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3014.asesment_mobpro.database.KritikSaranDao
import org.d3if3014.asesment_mobpro.model.KritikSaran

class MainViewModel(dao: KritikSaranDao) : ViewModel() {

    val data: StateFlow<List<KritikSaran>> = dao.getKritikSaran().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}
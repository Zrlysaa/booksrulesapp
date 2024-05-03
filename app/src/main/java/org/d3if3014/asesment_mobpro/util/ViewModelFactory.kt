package org.d3if3014.asesment_mobpro.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3014.asesment_mobpro.database.KritikSaranDao
import org.d3if3014.asesment_mobpro.ui.theme.Screen.DetailViewModel
import org.d3if3014.asesment_mobpro.ui.theme.Screen.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (
    private val dao: KritikSaranDao
) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(dao) as T
            } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")

        }
    }


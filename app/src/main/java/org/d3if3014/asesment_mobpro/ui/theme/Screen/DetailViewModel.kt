package org.d3if3014.asesment_mobpro.ui.theme.Screen

import androidx.lifecycle.ViewModel
import org.d3if3014.asesment_mobpro.model.KritikSaran

class DetailViewModel : ViewModel() {
    fun getKtitikSaran(id: Long): KritikSaran {
        return KritikSaran(
            id,
            "Siti Zahrah Alysa S",
            "12-12-2024",
            "Mal(Harta)",
            "Lebih banyak zakatnya lagi kedepannya"
        )
    }
}
package org.d3if3014.asesment_mobpro.ui.theme.Screen

import androidx.lifecycle.ViewModel
import org.d3if3014.asesment_mobpro.model.KritikSaran

class MainViewModel: ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<KritikSaran> {
        val data = mutableListOf<KritikSaran>()
        for (i in 29 downTo 20) {
            data.add(
                KritikSaran(
                    i.toLong(),
                    " $i Siti Zahrah Alysa S",
                    "12-12-2024",
                    "Mal(Harta)",
                    "Lebih banyak zakatnya lagi kedepannya"
                )
            )
        }
        return data
    }
}
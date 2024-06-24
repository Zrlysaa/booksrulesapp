package org.d3if3014.asesment_mobpro.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "kritikSaran")
data class KritikSaran (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val namamu: String,
    val tanggal: String,
    val zakat: String,
    val target: String
)

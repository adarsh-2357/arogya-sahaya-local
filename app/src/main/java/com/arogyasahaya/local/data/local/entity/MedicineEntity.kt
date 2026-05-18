package com.arogyasahaya.local.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "dosage")
    val dosage: String,

    @ColumnInfo(name = "times_of_day")
    val timesOfDay: List<String>,

    @ColumnInfo(name = "start_date")
    val startDate: Long,

    @ColumnInfo(name = "is_active")
    val isActive: Boolean = true
)

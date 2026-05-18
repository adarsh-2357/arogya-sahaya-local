package com.arogyasahaya.local.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vital_logs")
data class VitalLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "blood_pressure_systolic")
    val bloodPressureSystolic: Int?,

    @ColumnInfo(name = "blood_pressure_diastolic")
    val bloodPressureDiastolic: Int?,

    @ColumnInfo(name = "heart_rate")
    val heartRate: Int?,

    @ColumnInfo(name = "glucose_level")
    val glucoseLevel: Float?,

    @ColumnInfo(name = "notes")
    val notes: String = ""
)

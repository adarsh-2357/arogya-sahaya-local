package com.arogyasahaya.local.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "health_records")
data class HealthRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "systolic_bp")
    val systolicBp: Int,

    @ColumnInfo(name = "diastolic_bp")
    val diastolicBp: Int,

    @ColumnInfo(name = "heart_rate")
    val heartRate: Int,

    @ColumnInfo(name = "blood_sugar")
    val bloodSugar: Float,

    @ColumnInfo(name = "temperature")
    val temperature: Float,

    @ColumnInfo(name = "notes")
    val notes: String = "",

    @ColumnInfo(name = "recorded_at")
    val recordedAt: Date = Date()
)

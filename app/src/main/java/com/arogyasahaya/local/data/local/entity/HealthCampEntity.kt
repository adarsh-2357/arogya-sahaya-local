package com.arogyasahaya.local.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_camps")
data class HealthCampEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "camp_name")
    val campName: String,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "asha_worker_name")
    val ashaWorkerName: String,

    @ColumnInfo(name = "description")
    val description: String = ""
)

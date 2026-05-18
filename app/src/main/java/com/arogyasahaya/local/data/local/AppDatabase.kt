package com.arogyasahaya.local.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arogyasahaya.local.data.local.dao.HealthRecordDao
import com.arogyasahaya.local.data.local.entity.HealthRecord

@Database(
    entities = [HealthRecord::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun healthRecordDao(): HealthRecordDao
}

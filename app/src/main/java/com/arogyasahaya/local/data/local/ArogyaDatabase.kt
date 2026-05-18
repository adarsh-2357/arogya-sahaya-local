package com.arogyasahaya.local.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arogyasahaya.local.data.local.dao.HealthCampDao
import com.arogyasahaya.local.data.local.dao.MedicineDao
import com.arogyasahaya.local.data.local.dao.VitalLogDao
import com.arogyasahaya.local.data.local.entity.HealthCampEntity
import com.arogyasahaya.local.data.local.entity.MedicineEntity
import com.arogyasahaya.local.data.local.entity.VitalLogEntity

@Database(
    entities = [
        MedicineEntity::class,
        VitalLogEntity::class,
        HealthCampEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ArogyaDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao

    abstract fun vitalLogDao(): VitalLogDao

    abstract fun healthCampDao(): HealthCampDao
}

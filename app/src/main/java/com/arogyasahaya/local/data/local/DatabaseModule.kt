package com.arogyasahaya.local.data.local

import android.content.Context
import androidx.room.Room
import com.arogyasahaya.local.data.local.dao.HealthCampDao
import com.arogyasahaya.local.data.local.dao.MedicineDao
import com.arogyasahaya.local.data.local.dao.VitalLogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideArogyaDatabase(
        @ApplicationContext context: Context
    ): ArogyaDatabase {
        return Room.databaseBuilder(
            context,
            ArogyaDatabase::class.java,
            "arogya_sahaya_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMedicineDao(database: ArogyaDatabase): MedicineDao {
        return database.medicineDao()
    }

    @Provides
    @Singleton
    fun provideVitalLogDao(database: ArogyaDatabase): VitalLogDao {
        return database.vitalLogDao()
    }

    @Provides
    @Singleton
    fun provideHealthCampDao(database: ArogyaDatabase): HealthCampDao {
        return database.healthCampDao()
    }
}

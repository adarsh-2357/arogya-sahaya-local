package com.arogyasahaya.local.di

import android.content.Context
import androidx.room.Room
import com.arogyasahaya.local.data.local.AppDatabase
import com.arogyasahaya.local.data.local.dao.HealthRecordDao
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
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "arogya_sahaya_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideHealthRecordDao(database: AppDatabase): HealthRecordDao {
        return database.healthRecordDao()
    }
}

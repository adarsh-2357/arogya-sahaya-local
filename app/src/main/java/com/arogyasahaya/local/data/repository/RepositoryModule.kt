package com.arogyasahaya.local.data.repository

import com.arogyasahaya.local.data.local.dao.HealthCampDao
import com.arogyasahaya.local.data.local.dao.MedicineDao
import com.arogyasahaya.local.data.local.dao.VitalLogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMedicineRepository(
        medicineDao: MedicineDao
    ): MedicineRepository {
        return MedicineRepository(medicineDao)
    }

    @Provides
    @Singleton
    fun provideVitalLogRepository(
        vitalLogDao: VitalLogDao
    ): VitalLogRepository {
        return VitalLogRepository(vitalLogDao)
    }

    @Provides
    @Singleton
    fun provideHealthCampRepository(
        healthCampDao: HealthCampDao
    ): HealthCampRepository {
        return HealthCampRepository(healthCampDao)
    }
}

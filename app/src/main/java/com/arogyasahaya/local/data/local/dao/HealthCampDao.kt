package com.arogyasahaya.local.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arogyasahaya.local.data.local.entity.HealthCampEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthCampDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(healthCamp: HealthCampEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(healthCamps: List<HealthCampEntity>): List<Long>

    @Update
    suspend fun update(healthCamp: HealthCampEntity)

    @Delete
    suspend fun delete(healthCamp: HealthCampEntity)

    @Query("DELETE FROM health_camps WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM health_camps ORDER BY date DESC")
    fun getAllHealthCamps(): Flow<List<HealthCampEntity>>

    @Query("SELECT * FROM health_camps WHERE id = :id")
    suspend fun getHealthCampById(id: Long): HealthCampEntity?

    @Query("SELECT * FROM health_camps WHERE id = :id")
    fun getHealthCampByIdFlow(id: Long): Flow<HealthCampEntity?>

    @Query("SELECT * FROM health_camps WHERE date >= :currentDate ORDER BY date ASC")
    fun getUpcomingHealthCamps(currentDate: Long): Flow<List<HealthCampEntity>>

    @Query("SELECT * FROM health_camps WHERE date < :currentDate ORDER BY date DESC")
    fun getPastHealthCamps(currentDate: Long): Flow<List<HealthCampEntity>>

    @Query("SELECT * FROM health_camps WHERE date BETWEEN :startDate AND :endDate ORDER BY date ASC")
    fun getHealthCampsBetweenDates(startDate: Long, endDate: Long): Flow<List<HealthCampEntity>>

    @Query("SELECT * FROM health_camps WHERE location LIKE '%' || :query || '%' OR camp_name LIKE '%' || :query || '%' ORDER BY date DESC")
    fun searchHealthCamps(query: String): Flow<List<HealthCampEntity>>

    @Query("SELECT * FROM health_camps WHERE asha_worker_name = :ashaWorkerName ORDER BY date DESC")
    fun getHealthCampsByAshaWorker(ashaWorkerName: String): Flow<List<HealthCampEntity>>

    @Query("SELECT DISTINCT asha_worker_name FROM health_camps ORDER BY asha_worker_name ASC")
    fun getAllAshaWorkerNames(): Flow<List<String>>

    @Query("SELECT DISTINCT location FROM health_camps ORDER BY location ASC")
    fun getAllLocations(): Flow<List<String>>

    @Query("SELECT COUNT(*) FROM health_camps")
    fun getHealthCampCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM health_camps WHERE date >= :currentDate")
    fun getUpcomingHealthCampCount(currentDate: Long): Flow<Int>

    @Query("DELETE FROM health_camps")
    suspend fun deleteAllHealthCamps()
}

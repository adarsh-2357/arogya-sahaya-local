package com.arogyasahaya.local.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arogyasahaya.local.data.local.entity.VitalLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vitalLog: VitalLogEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vitalLogs: List<VitalLogEntity>): List<Long>

    @Update
    suspend fun update(vitalLog: VitalLogEntity)

    @Delete
    suspend fun delete(vitalLog: VitalLogEntity)

    @Query("DELETE FROM vital_logs WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM vital_logs ORDER BY date DESC")
    fun getAllVitalLogs(): Flow<List<VitalLogEntity>>

    @Query("SELECT * FROM vital_logs WHERE id = :id")
    suspend fun getVitalLogById(id: Long): VitalLogEntity?

    @Query("SELECT * FROM vital_logs WHERE id = :id")
    fun getVitalLogByIdFlow(id: Long): Flow<VitalLogEntity?>

    @Query("SELECT * FROM vital_logs WHERE date >= :since ORDER BY date DESC")
    fun getVitalLogsForLast7Days(since: Long): Flow<List<VitalLogEntity>>

    @Query("SELECT * FROM vital_logs WHERE date BETWEEN :startDate AND :endDate ORDER BY date ASC")
    fun getVitalLogsBetweenDates(startDate: Long, endDate: Long): Flow<List<VitalLogEntity>>

    @Query("SELECT * FROM vital_logs ORDER BY date DESC LIMIT :limit")
    fun getRecentVitalLogs(limit: Int): Flow<List<VitalLogEntity>>

    @Query("SELECT * FROM vital_logs ORDER BY date DESC LIMIT 1")
    fun getLatestVitalLog(): Flow<VitalLogEntity?>

    @Query("SELECT AVG(blood_pressure_systolic) FROM vital_logs WHERE date >= :since AND blood_pressure_systolic IS NOT NULL")
    fun getAverageSystolicBP(since: Long): Flow<Float?>

    @Query("SELECT AVG(blood_pressure_diastolic) FROM vital_logs WHERE date >= :since AND blood_pressure_diastolic IS NOT NULL")
    fun getAverageDiastolicBP(since: Long): Flow<Float?>

    @Query("SELECT AVG(heart_rate) FROM vital_logs WHERE date >= :since AND heart_rate IS NOT NULL")
    fun getAverageHeartRate(since: Long): Flow<Float?>

    @Query("SELECT AVG(glucose_level) FROM vital_logs WHERE date >= :since AND glucose_level IS NOT NULL")
    fun getAverageGlucoseLevel(since: Long): Flow<Float?>

    @Query("SELECT COUNT(*) FROM vital_logs")
    fun getVitalLogCount(): Flow<Int>

    @Query("DELETE FROM vital_logs")
    suspend fun deleteAllVitalLogs()
}

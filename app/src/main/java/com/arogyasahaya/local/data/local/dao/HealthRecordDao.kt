package com.arogyasahaya.local.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arogyasahaya.local.data.local.entity.HealthRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: HealthRecord): Long

    @Update
    suspend fun update(record: HealthRecord)

    @Delete
    suspend fun delete(record: HealthRecord)

    @Query("SELECT * FROM health_records ORDER BY recorded_at DESC")
    fun getAllRecords(): Flow<List<HealthRecord>>

    @Query("SELECT * FROM health_records WHERE id = :id")
    suspend fun getRecordById(id: Long): HealthRecord?

    @Query("SELECT * FROM health_records WHERE recorded_at BETWEEN :startDate AND :endDate ORDER BY recorded_at ASC")
    fun getRecordsBetween(startDate: Long, endDate: Long): Flow<List<HealthRecord>>

    @Query("SELECT COUNT(*) FROM health_records")
    suspend fun getRecordCount(): Int

    @Query("DELETE FROM health_records")
    suspend fun deleteAllRecords()
}

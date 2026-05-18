package com.arogyasahaya.local.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arogyasahaya.local.data.local.entity.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(medicine: MedicineEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<MedicineEntity>): List<Long>

    @Update
    suspend fun update(medicine: MedicineEntity)

    @Delete
    suspend fun delete(medicine: MedicineEntity)

    @Query("DELETE FROM medicines WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM medicines ORDER BY start_date DESC")
    fun getAllMedicines(): Flow<List<MedicineEntity>>

    @Query("SELECT * FROM medicines WHERE is_active = 1 ORDER BY name ASC")
    fun getActiveMedicines(): Flow<List<MedicineEntity>>

    @Query("SELECT * FROM medicines WHERE is_active = 0 ORDER BY name ASC")
    fun getInactiveMedicines(): Flow<List<MedicineEntity>>

    @Query("SELECT * FROM medicines WHERE id = :id")
    suspend fun getMedicineById(id: Long): MedicineEntity?

    @Query("SELECT * FROM medicines WHERE id = :id")
    fun getMedicineByIdFlow(id: Long): Flow<MedicineEntity?>

    @Query("SELECT * FROM medicines WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchMedicines(query: String): Flow<List<MedicineEntity>>

    @Query("UPDATE medicines SET is_active = :isActive WHERE id = :id")
    suspend fun updateActiveStatus(id: Long, isActive: Boolean)

    @Query("SELECT COUNT(*) FROM medicines WHERE is_active = 1")
    fun getActiveMedicineCount(): Flow<Int>

    @Query("DELETE FROM medicines")
    suspend fun deleteAllMedicines()
}

package com.arogyasahaya.local.data.repository

import com.arogyasahaya.local.data.local.dao.MedicineDao
import com.arogyasahaya.local.data.local.entity.MedicineEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicineRepository @Inject constructor(
    private val medicineDao: MedicineDao
) {

    fun getAllMedicines(): Flow<List<MedicineEntity>> {
        return medicineDao.getAllMedicines()
    }

    fun getActiveMedicines(): Flow<List<MedicineEntity>> {
        return medicineDao.getActiveMedicines()
    }

    fun getInactiveMedicines(): Flow<List<MedicineEntity>> {
        return medicineDao.getInactiveMedicines()
    }

    fun getMedicineById(id: Long): Flow<MedicineEntity?> {
        return medicineDao.getMedicineByIdFlow(id)
    }

    fun searchMedicines(query: String): Flow<List<MedicineEntity>> {
        return medicineDao.searchMedicines(query)
    }

    fun getActiveMedicineCount(): Flow<Int> {
        return medicineDao.getActiveMedicineCount()
    }

    suspend fun insertMedicine(medicine: MedicineEntity): Long {
        return medicineDao.insert(medicine)
    }

    suspend fun insertAllMedicines(medicines: List<MedicineEntity>): List<Long> {
        return medicineDao.insertAll(medicines)
    }

    suspend fun updateMedicine(medicine: MedicineEntity) {
        medicineDao.update(medicine)
    }

    suspend fun deleteMedicine(medicine: MedicineEntity) {
        medicineDao.delete(medicine)
    }

    suspend fun deleteMedicineById(id: Long) {
        medicineDao.deleteById(id)
    }

    suspend fun updateActiveStatus(id: Long, isActive: Boolean) {
        medicineDao.updateActiveStatus(id, isActive)
    }

    suspend fun deleteAllMedicines() {
        medicineDao.deleteAllMedicines()
    }
}

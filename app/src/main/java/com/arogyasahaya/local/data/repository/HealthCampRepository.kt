package com.arogyasahaya.local.data.repository

import android.content.Context
import com.arogyasahaya.local.data.local.dao.HealthCampDao
import com.arogyasahaya.local.data.local.entity.HealthCampEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HealthCampRepository @Inject constructor(
    private val healthCampDao: HealthCampDao
) {

    companion object {
        private const val ONE_DAY_MILLIS = 24 * 60 * 60 * 1000L
    }

    fun getAllCamps(): Flow<List<HealthCampEntity>> {
        return healthCampDao.getAllHealthCamps()
    }

    fun getUpcomingCamps(): Flow<List<HealthCampEntity>> {
        return healthCampDao.getUpcomingHealthCamps(System.currentTimeMillis())
    }

    fun getPastCamps(): Flow<List<HealthCampEntity>> {
        return healthCampDao.getPastHealthCamps(System.currentTimeMillis())
    }

    fun getCampsBetweenDates(startDate: Long, endDate: Long): Flow<List<HealthCampEntity>> {
        return healthCampDao.getHealthCampsBetweenDates(startDate, endDate)
    }

    fun searchCamps(query: String): Flow<List<HealthCampEntity>> {
        return healthCampDao.searchHealthCamps(query)
    }

    fun getCampsByAshaWorker(ashaWorkerName: String): Flow<List<HealthCampEntity>> {
        return healthCampDao.getHealthCampsByAshaWorker(ashaWorkerName)
    }

    fun getCampById(id: Long): Flow<HealthCampEntity?> {
        return healthCampDao.getHealthCampByIdFlow(id)
    }

    fun getAllAshaWorkerNames(): Flow<List<String>> {
        return healthCampDao.getAllAshaWorkerNames()
    }

    fun getAllLocations(): Flow<List<String>> {
        return healthCampDao.getAllLocations()
    }

    fun getCampCount(): Flow<Int> {
        return healthCampDao.getHealthCampCount()
    }

    fun getUpcomingCampCount(): Flow<Int> {
        return healthCampDao.getUpcomingHealthCampCount(System.currentTimeMillis())
    }

    suspend fun insertCamp(camp: HealthCampEntity): Long {
        return healthCampDao.insert(camp)
    }

    suspend fun insertAllCamps(camps: List<HealthCampEntity>): List<Long> {
        return healthCampDao.insertAll(camps)
    }

    suspend fun updateCamp(camp: HealthCampEntity) {
        healthCampDao.update(camp)
    }

    suspend fun deleteCamp(camp: HealthCampEntity) {
        healthCampDao.delete(camp)
    }

    suspend fun deleteCampById(id: Long) {
        healthCampDao.deleteById(id)
    }

    suspend fun deleteAllCamps() {
        healthCampDao.deleteAllHealthCamps()
    }

    suspend fun seedSampleCamps(context: Context) {
        val existingCamps = healthCampDao.getAllHealthCamps().first()
        if (existingCamps.isNotEmpty()) {
            return
        }

        val currentTime = System.currentTimeMillis()

        val sampleCamps = listOf(
            HealthCampEntity(
                campName = "Maternal Health Checkup Camp",
                location = "Rampur Village, Block Sadar",
                date = currentTime + (2 * ONE_DAY_MILLIS),
                ashaWorkerName = "Sunita Devi",
                description = "Free antenatal checkups, iron-folic acid distribution, and pregnancy counseling for expecting mothers. Vaccination for infants."
            ),
            HealthCampEntity(
                campName = "Diabetes Screening Camp",
                location = "Kishanganj Gram Panchayat",
                date = currentTime + (5 * ONE_DAY_MILLIS),
                ashaWorkerName = "Meera Kumari",
                description = "Free blood sugar testing, HbA1c screening, dietary counseling, and diabetes awareness session for adults above 35 years."
            ),
            HealthCampEntity(
                campName = "Child Immunization Drive",
                location = "Lakshmipur Primary School",
                date = currentTime + (7 * ONE_DAY_MILLIS),
                ashaWorkerName = "Geeta Sharma",
                description = "Routine immunization for children 0-5 years including BCG, OPV, Pentavalent, and Measles vaccines. Growth monitoring included."
            ),
            HealthCampEntity(
                campName = "Eye Care Camp",
                location = "Mohanpur Community Center",
                date = currentTime + (10 * ONE_DAY_MILLIS),
                ashaWorkerName = "Radha Verma",
                description = "Free eye checkup, cataract screening, spectacle distribution for elderly. Referrals to district hospital for surgeries."
            ),
            HealthCampEntity(
                campName = "Hypertension Awareness Camp",
                location = "Gandhigram Aanganwadi Center",
                date = currentTime + (14 * ONE_DAY_MILLIS),
                ashaWorkerName = "Kamla Yadav",
                description = "Blood pressure screening, salt intake counseling, lifestyle modification guidance. Free BP monitoring devices for high-risk patients."
            ),
            HealthCampEntity(
                campName = "Tuberculosis Detection Camp",
                location = "Nehru Chowk, Bhagwanpur",
                date = currentTime + (18 * ONE_DAY_MILLIS),
                ashaWorkerName = "Savitri Paswan",
                description = "Sputum collection for TB testing, chest X-ray referrals, DOTS treatment enrollment. Contact tracing and family screening."
            ),
            HealthCampEntity(
                campName = "Women's Health Camp",
                location = "Shivaji Nagar Community Hall",
                date = currentTime + (23 * ONE_DAY_MILLIS),
                ashaWorkerName = "Poonam Singh",
                description = "Cervical cancer screening, breast examination, menstrual hygiene education, family planning counseling, and contraceptive distribution."
            ),
            HealthCampEntity(
                campName = "General Health Mela",
                location = "Ambedkar Chauraha, Sultanpur",
                date = currentTime + (28 * ONE_DAY_MILLIS),
                ashaWorkerName = "Anita Gupta",
                description = "Comprehensive health checkup including BP, blood sugar, BMI, dental checkup. Medicine distribution, health card issuance, and Ayushman Bharat enrollment."
            )
        )

        healthCampDao.insertAll(sampleCamps)
    }
}

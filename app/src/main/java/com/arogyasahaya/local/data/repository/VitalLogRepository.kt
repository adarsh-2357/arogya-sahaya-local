package com.arogyasahaya.local.data.repository

import com.arogyasahaya.local.data.local.dao.VitalLogDao
import com.arogyasahaya.local.data.local.entity.VitalLogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VitalLogRepository @Inject constructor(
    private val vitalLogDao: VitalLogDao
) {

    companion object {
        private const val SEVEN_DAYS_MILLIS = 7 * 24 * 60 * 60 * 1000L
        private const val THIRTY_DAYS_MILLIS = 30 * 24 * 60 * 60 * 1000L
    }

    fun getAllLogs(): Flow<List<VitalLogEntity>> {
        return vitalLogDao.getAllVitalLogs()
    }

    fun getLogsForLast7Days(): Flow<List<VitalLogEntity>> {
        val sevenDaysAgo = System.currentTimeMillis() - SEVEN_DAYS_MILLIS
        return vitalLogDao.getVitalLogsForLast7Days(sevenDaysAgo)
    }

    fun getLogsForLast30Days(): Flow<List<VitalLogEntity>> {
        val thirtyDaysAgo = System.currentTimeMillis() - THIRTY_DAYS_MILLIS
        return vitalLogDao.getVitalLogsForLast7Days(thirtyDaysAgo)
    }

    fun getLogsBetweenDates(startDate: Long, endDate: Long): Flow<List<VitalLogEntity>> {
        return vitalLogDao.getVitalLogsBetweenDates(startDate, endDate)
    }

    fun getRecentLogs(limit: Int): Flow<List<VitalLogEntity>> {
        return vitalLogDao.getRecentVitalLogs(limit)
    }

    fun getLatestLog(): Flow<VitalLogEntity?> {
        return vitalLogDao.getLatestVitalLog()
    }

    fun getLogById(id: Long): Flow<VitalLogEntity?> {
        return vitalLogDao.getVitalLogByIdFlow(id)
    }

    fun getLogCount(): Flow<Int> {
        return vitalLogDao.getVitalLogCount()
    }

    fun getAverageSystolicBPLast7Days(): Flow<Float?> {
        val sevenDaysAgo = System.currentTimeMillis() - SEVEN_DAYS_MILLIS
        return vitalLogDao.getAverageSystolicBP(sevenDaysAgo)
    }

    fun getAverageDiastolicBPLast7Days(): Flow<Float?> {
        val sevenDaysAgo = System.currentTimeMillis() - SEVEN_DAYS_MILLIS
        return vitalLogDao.getAverageDiastolicBP(sevenDaysAgo)
    }

    fun getAverageHeartRateLast7Days(): Flow<Float?> {
        val sevenDaysAgo = System.currentTimeMillis() - SEVEN_DAYS_MILLIS
        return vitalLogDao.getAverageHeartRate(sevenDaysAgo)
    }

    fun getAverageGlucoseLast7Days(): Flow<Float?> {
        val sevenDaysAgo = System.currentTimeMillis() - SEVEN_DAYS_MILLIS
        return vitalLogDao.getAverageGlucoseLevel(sevenDaysAgo)
    }

    suspend fun insertVitalLog(log: VitalLogEntity): Long {
        return vitalLogDao.insert(log)
    }

    suspend fun insertAllLogs(logs: List<VitalLogEntity>): List<Long> {
        return vitalLogDao.insertAll(logs)
    }

    suspend fun updateVitalLog(log: VitalLogEntity) {
        vitalLogDao.update(log)
    }

    suspend fun deleteVitalLog(log: VitalLogEntity) {
        vitalLogDao.delete(log)
    }

    suspend fun deleteVitalLogById(id: Long) {
        vitalLogDao.deleteById(id)
    }

    suspend fun deleteAllLogs() {
        vitalLogDao.deleteAllVitalLogs()
    }
}

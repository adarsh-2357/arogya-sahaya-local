package com.arogyasahaya.local.ui.vitals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arogyasahaya.local.data.local.entity.VitalLogEntity
import com.arogyasahaya.local.data.repository.VitalLogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class VitalsViewModel @Inject constructor(
    private val vitalLogRepository: VitalLogRepository
) : ViewModel() {

    val last7DaysLogs: StateFlow<List<VitalLogEntity>> = vitalLogRepository
        .getLogsForLast7Days()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val allLogs: StateFlow<List<VitalLogEntity>> = vitalLogRepository
        .getAllLogs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val latestLog: StateFlow<VitalLogEntity?> = vitalLogRepository
        .getLatestLog()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val logCount: StateFlow<Int> = vitalLogRepository
        .getLogCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val averageSystolicBP: StateFlow<Float?> = vitalLogRepository
        .getAverageSystolicBPLast7Days()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val averageDiastolicBP: StateFlow<Float?> = vitalLogRepository
        .getAverageDiastolicBPLast7Days()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val averageHeartRate: StateFlow<Float?> = vitalLogRepository
        .getAverageHeartRateLast7Days()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val averageGlucose: StateFlow<Float?> = vitalLogRepository
        .getAverageGlucoseLast7Days()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _logSuccess = MutableStateFlow(false)
    val logSuccess: StateFlow<Boolean> = _logSuccess.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun logVital(
        systolic: Int?,
        diastolic: Int?,
        heartRate: Int?,
        glucose: Float?,
        notes: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val hasData = systolic != null || diastolic != null ||
                        heartRate != null || glucose != null

                if (!hasData) {
                    _errorMessage.value = "Please enter at least one vital sign"
                    _isLoading.value = false
                    return@launch
                }

                val vitalLog = VitalLogEntity(
                    date = System.currentTimeMillis(),
                    bloodPressureSystolic = systolic,
                    bloodPressureDiastolic = diastolic,
                    heartRate = heartRate,
                    glucoseLevel = glucose,
                    notes = notes.trim()
                )

                vitalLogRepository.insertVitalLog(vitalLog)
                _logSuccess.value = true
            } catch (e: Exception) {
                _errorMessage.value = "Failed to log vitals: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteVitalLog(log: VitalLogEntity) {
        viewModelScope.launch {
            try {
                vitalLogRepository.deleteVitalLog(log)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete log: ${e.message}"
            }
        }
    }

    fun resetLogSuccess() {
        _logSuccess.value = false
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun getLogsForChart(): List<VitalLogEntity> {
        return last7DaysLogs.value.sortedBy { it.date }
    }
}

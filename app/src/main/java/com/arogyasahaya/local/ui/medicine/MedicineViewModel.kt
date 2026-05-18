package com.arogyasahaya.local.ui.medicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arogyasahaya.local.data.local.entity.MedicineEntity
import com.arogyasahaya.local.data.repository.MedicineRepository
import com.arogyasahaya.local.util.AlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val medicineRepository: MedicineRepository,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {

    val activeMedicines: StateFlow<List<MedicineEntity>> = medicineRepository
        .getActiveMedicines()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val allMedicines: StateFlow<List<MedicineEntity>> = medicineRepository
        .getAllMedicines()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val activeMedicineCount: StateFlow<Int> = medicineRepository
        .getActiveMedicineCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    private val _addMedicineSuccess = MutableStateFlow(false)
    val addMedicineSuccess: StateFlow<Boolean> = _addMedicineSuccess.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun addMedicine(name: String, dosage: String, timesOfDay: List<String>) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val medicine = MedicineEntity(
                    name = name.trim(),
                    dosage = dosage.trim(),
                    timesOfDay = timesOfDay,
                    startDate = System.currentTimeMillis(),
                    isActive = true
                )

                val insertedId = medicineRepository.insertMedicine(medicine)

                alarmScheduler.scheduleMedicineAlarms(
                    medicineId = insertedId,
                    medicineName = medicine.name,
                    dosage = medicine.dosage,
                    timesOfDay = medicine.timesOfDay
                )

                _addMedicineSuccess.value = true
            } catch (e: Exception) {
                _errorMessage.value = "Failed to add medicine: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteMedicine(medicine: MedicineEntity) {
        viewModelScope.launch {
            try {
                alarmScheduler.cancelAllAlarmsForMedicine(medicine.id)
                medicineRepository.deleteMedicine(medicine)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete medicine: ${e.message}"
            }
        }
    }

    fun toggleActive(medicine: MedicineEntity) {
        viewModelScope.launch {
            try {
                val newActiveState = !medicine.isActive
                medicineRepository.updateActiveStatus(medicine.id, newActiveState)

                if (newActiveState) {
                    alarmScheduler.scheduleMedicineAlarms(
                        medicineId = medicine.id,
                        medicineName = medicine.name,
                        dosage = medicine.dosage,
                        timesOfDay = medicine.timesOfDay
                    )
                } else {
                    alarmScheduler.cancelAllAlarmsForMedicine(medicine.id)
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to update medicine: ${e.message}"
            }
        }
    }

    fun resetAddMedicineSuccess() {
        _addMedicineSuccess.value = false
    }

    fun clearError() {
        _errorMessage.value = null
    }
}

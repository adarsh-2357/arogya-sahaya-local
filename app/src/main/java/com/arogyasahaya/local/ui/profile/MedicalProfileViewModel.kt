package com.arogyasahaya.local.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arogyasahaya.local.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicalProfileViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val userName: StateFlow<String> = dataStoreManager.userName
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    val age: StateFlow<Int> = dataStoreManager.age
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val bloodGroup: StateFlow<String> = dataStoreManager.bloodGroup
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    val chronicConditions: StateFlow<String> = dataStoreManager.chronicConditions
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    val emergencyContact: StateFlow<String> = dataStoreManager.emergencyContact
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    val emergencyContactName: StateFlow<String> = dataStoreManager.emergencyContactName
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun updateName(name: String) {
        viewModelScope.launch {
            dataStoreManager.updateUserName(name)
        }
    }

    fun updateAge(age: Int) {
        viewModelScope.launch {
            dataStoreManager.updateAge(age)
        }
    }

    fun updateBloodGroup(bloodGroup: String) {
        viewModelScope.launch {
            dataStoreManager.updateBloodGroup(bloodGroup)
        }
    }

    fun updateChronicConditions(conditions: String) {
        viewModelScope.launch {
            dataStoreManager.updateChronicConditions(conditions)
        }
    }

    fun updateEmergencyContact(contact: String) {
        viewModelScope.launch {
            dataStoreManager.updateEmergencyContact(contact)
        }
    }

    fun updateEmergencyContactName(name: String) {
        viewModelScope.launch {
            dataStoreManager.updateEmergencyContactName(name)
        }
    }

    fun saveAllProfile(
        userName: String,
        age: Int,
        bloodGroup: String,
        chronicConditions: String,
        emergencyContact: String,
        emergencyContactName: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                dataStoreManager.updateAllProfile(
                    userName = userName,
                    age = age,
                    bloodGroup = bloodGroup,
                    chronicConditions = chronicConditions,
                    emergencyContact = emergencyContact,
                    emergencyContactName = emergencyContactName
                )
                _saveSuccess.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetSaveSuccess() {
        _saveSuccess.value = false
    }

    fun clearProfile() {
        viewModelScope.launch {
            dataStoreManager.clearAllData()
        }
    }
}

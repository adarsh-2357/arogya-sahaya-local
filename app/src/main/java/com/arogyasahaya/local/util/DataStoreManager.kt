package com.arogyasahaya.local.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "arogya_user_preferences"
)

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private object PreferencesKeys {
        val USER_NAME = stringPreferencesKey("user_name")
        val AGE = intPreferencesKey("age")
        val BLOOD_GROUP = stringPreferencesKey("blood_group")
        val CHRONIC_CONDITIONS = stringPreferencesKey("chronic_conditions")
        val EMERGENCY_CONTACT = stringPreferencesKey("emergency_contact")
        val EMERGENCY_CONTACT_NAME = stringPreferencesKey("emergency_contact_name")
    }

    val userName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.USER_NAME] ?: ""
    }

    val age: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.AGE] ?: 0
    }

    val bloodGroup: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.BLOOD_GROUP] ?: ""
    }

    val chronicConditions: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.CHRONIC_CONDITIONS] ?: ""
    }

    val emergencyContact: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.EMERGENCY_CONTACT] ?: ""
    }

    val emergencyContactName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.EMERGENCY_CONTACT_NAME] ?: ""
    }

    suspend fun updateUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_NAME] = name
        }
    }

    suspend fun updateAge(age: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.AGE] = age
        }
    }

    suspend fun updateBloodGroup(bloodGroup: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.BLOOD_GROUP] = bloodGroup
        }
    }

    suspend fun updateChronicConditions(conditions: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.CHRONIC_CONDITIONS] = conditions
        }
    }

    suspend fun updateEmergencyContact(contact: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.EMERGENCY_CONTACT] = contact
        }
    }

    suspend fun updateEmergencyContactName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.EMERGENCY_CONTACT_NAME] = name
        }
    }

    suspend fun updateAllProfile(
        userName: String,
        age: Int,
        bloodGroup: String,
        chronicConditions: String,
        emergencyContact: String,
        emergencyContactName: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_NAME] = userName
            preferences[PreferencesKeys.AGE] = age
            preferences[PreferencesKeys.BLOOD_GROUP] = bloodGroup
            preferences[PreferencesKeys.CHRONIC_CONDITIONS] = chronicConditions
            preferences[PreferencesKeys.EMERGENCY_CONTACT] = emergencyContact
            preferences[PreferencesKeys.EMERGENCY_CONTACT_NAME] = emergencyContactName
        }
    }

    suspend fun clearAllData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

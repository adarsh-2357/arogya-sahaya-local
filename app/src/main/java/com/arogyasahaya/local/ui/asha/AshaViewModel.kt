package com.arogyasahaya.local.ui.asha

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arogyasahaya.local.data.local.entity.HealthCampEntity
import com.arogyasahaya.local.data.repository.HealthCampRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AshaViewModel @Inject constructor(
    private val healthCampRepository: HealthCampRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val calendar = Calendar.getInstance()

    private val _selectedMonth = MutableStateFlow(calendar.get(Calendar.MONTH))
    val selectedMonth: StateFlow<Int> = _selectedMonth.asStateFlow()

    private val _selectedYear = MutableStateFlow(calendar.get(Calendar.YEAR))
    val selectedYear: StateFlow<Int> = _selectedYear.asStateFlow()

    private val _selectedDay = MutableStateFlow<Int?>(null)
    val selectedDay: StateFlow<Int?> = _selectedDay.asStateFlow()

    val upcomingCamps: StateFlow<List<HealthCampEntity>> = healthCampRepository
        .getUpcomingCamps()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val allCamps: StateFlow<List<HealthCampEntity>> = healthCampRepository
        .getAllCamps()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val campsByMonth: StateFlow<Map<Int, List<HealthCampEntity>>> = combine(
        allCamps,
        selectedMonth,
        selectedYear
    ) { camps, month, year ->
        camps
            .filter { camp ->
                val campCalendar = Calendar.getInstance().apply {
                    timeInMillis = camp.date
                }
                campCalendar.get(Calendar.MONTH) == month &&
                        campCalendar.get(Calendar.YEAR) == year
            }
            .groupBy { camp ->
                Calendar.getInstance().apply {
                    timeInMillis = camp.date
                }.get(Calendar.DAY_OF_MONTH)
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    val selectedDayCamps: StateFlow<List<HealthCampEntity>> = combine(
        campsByMonth,
        selectedDay
    ) { campsByMonth, selectedDay ->
        selectedDay?.let { campsByMonth[it] ?: emptyList() } ?: emptyList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val campCount: StateFlow<Int> = healthCampRepository
        .getCampCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val upcomingCampCount: StateFlow<Int> = healthCampRepository
        .getUpcomingCampCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    init {
        viewModelScope.launch {
            val existingCamps = allCamps.value
            if (existingCamps.isEmpty()) {
                healthCampRepository.seedSampleCamps(context)
            }
        }
    }

    fun selectMonth(month: Int) {
        _selectedMonth.value = month
        _selectedDay.value = null
    }

    fun selectYear(year: Int) {
        _selectedYear.value = year
        _selectedDay.value = null
    }

    fun selectDay(day: Int?) {
        _selectedDay.value = day
    }

    fun goToPreviousMonth() {
        val currentMonth = _selectedMonth.value
        val currentYear = _selectedYear.value

        if (currentMonth == Calendar.JANUARY) {
            _selectedMonth.value = Calendar.DECEMBER
            _selectedYear.value = currentYear - 1
        } else {
            _selectedMonth.value = currentMonth - 1
        }
        _selectedDay.value = null
    }

    fun goToNextMonth() {
        val currentMonth = _selectedMonth.value
        val currentYear = _selectedYear.value

        if (currentMonth == Calendar.DECEMBER) {
            _selectedMonth.value = Calendar.JANUARY
            _selectedYear.value = currentYear + 1
        } else {
            _selectedMonth.value = currentMonth + 1
        }
        _selectedDay.value = null
    }

    fun goToCurrentMonth() {
        val now = Calendar.getInstance()
        _selectedMonth.value = now.get(Calendar.MONTH)
        _selectedYear.value = now.get(Calendar.YEAR)
        _selectedDay.value = null
    }

    fun getDaysInMonth(): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, _selectedYear.value)
        cal.set(Calendar.MONTH, _selectedMonth.value)
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getFirstDayOfMonth(): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, _selectedYear.value)
        cal.set(Calendar.MONTH, _selectedMonth.value)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        return cal.get(Calendar.DAY_OF_WEEK)
    }

    fun hasCampOnDay(day: Int): Boolean {
        return campsByMonth.value.containsKey(day)
    }

    fun getMonthName(): String {
        val monthNames = listOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        )
        return monthNames.getOrElse(_selectedMonth.value) { "Unknown" }
    }
}

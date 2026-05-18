package com.arogyasahaya.local.ui.asha

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arogyasahaya.local.data.local.entity.HealthCampEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AshaConnectScreen(
    onNavigateBack: () -> Unit,
    viewModel: AshaViewModel = hiltViewModel()
) {
    val upcomingCamps by viewModel.upcomingCamps.collectAsState()
    val campsByMonth by viewModel.campsByMonth.collectAsState()
    val selectedDay by viewModel.selectedDay.collectAsState()
    val selectedMonth by viewModel.selectedMonth.collectAsState()
    val selectedYear by viewModel.selectedYear.collectAsState()
    val selectedDayCamps by viewModel.selectedDayCamps.collectAsState()

    val monthNames = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )

    val dayNames = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    val daysInMonth = viewModel.getDaysInMonth()
    val firstDayOfWeek = viewModel.getFirstDayOfMonth()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "ASHA Health Camps",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Upcoming visits & health camps in your area",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                            fontWeight = FontWeight.Normal
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Month Navigation Row
            item {
                MonthNavigationRow(
                    monthName = monthNames[selectedMonth],
                    year = selectedYear,
                    onPreviousMonth = { viewModel.goToPreviousMonth() },
                    onNextMonth = { viewModel.goToNextMonth() },
                    onCurrentMonth = { viewModel.goToCurrentMonth() }
                )
            }

            // Day Names Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    dayNames.forEach { dayName ->
                        Text(
                            text = dayName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Calendar Grid
            item {
                CalendarGrid(
                    daysInMonth = daysInMonth,
                    firstDayOffset = firstDayOfWeek - 1,
                    campsByDay = campsByMonth,
                    selectedDay = selectedDay,
                    onDaySelected = { viewModel.selectDay(it) }
                )
            }

            // Camp Count Summary
            item {
                val campCountForMonth = campsByMonth.values.flatten().size
                if (campCountForMonth > 0) {
                    Text(
                        text = "$campCountForMonth health camp${if (campCountForMonth != 1) "s" else ""} scheduled this month",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                } else {
                    Text(
                        text = "No camps scheduled this month",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Camp Detail Cards
            if (selectedDayCamps.isNotEmpty()) {
                items(selectedDayCamps) { camp ->
                    CampDetailCard(camp = camp)
                }
            }

            // Show all camps for the month if no day selected but camps exist
            if (selectedDay == null && campsByMonth.isNotEmpty()) {
                item {
                    Text(
                        text = "All camps this month:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                items(campsByMonth.values.flatten().sortedBy { it.date }) { camp ->
                    CampDetailCard(camp = camp, compact = true)
                }
            }

            // Empty state
            if (campsByMonth.isEmpty() && selectedDay == null) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "📅",
                                fontSize = 64.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "No camps scheduled",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Check back later for upcoming health camps in your area.",
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun MonthNavigationRow(
    monthName: String,
    year: Int,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onCurrentMonth: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onPreviousMonth,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Previous month",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(32.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$monthName $year",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Select a date to view camps",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = onCurrentMonth,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Today,
                        contentDescription = "Current month",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(
                    onClick = onNextMonth,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Next month",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CalendarGrid(
    daysInMonth: Int,
    firstDayOffset: Int,
    campsByDay: Map<Int, List<HealthCampEntity>>,
    selectedDay: Int?,
    onDaySelected: (Int) -> Unit
) {
    val totalCells = ((daysInMonth + firstDayOffset + 6) / 7) * 7

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (week in 0 until (totalCells / 7)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (dayInWeek in 0 until 7) {
                    val dayNumber = week * 7 + dayInWeek - firstDayOffset + 1

                    if (dayNumber in 1..daysInMonth) {
                        val hasCamp = campsByDay.containsKey(dayNumber)
                        val isSelected = selectedDay == dayNumber

                        DayCell(
                            day = dayNumber,
                            hasCamp = hasCamp,
                            isSelected = isSelected,
                            onClick = { onDaySelected(dayNumber) }
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.DayCell(
    day: Int,
    hasCamp: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (hasCamp) {
        Color(0xFF00897B)
    } else if (isSelected) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
    } else {
        Color.Transparent
    }

    val textColor = if (hasCamp) {
        Color.White
    } else if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .weight(1f)
            .clip(if (hasCamp) CircleShape else RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .border(
                width = if (isSelected && !hasCamp) 2.dp else 0.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = if (hasCamp) CircleShape else RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            fontSize = 18.sp,
            fontWeight = if (hasCamp) FontWeight.Bold else FontWeight.Normal,
            color = textColor
        )
    }
}

@Composable
private fun CampDetailCard(
    camp: HealthCampEntity,
    compact: Boolean = false
) {
    val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(camp.date))

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = camp.campName,
                fontSize = if (compact) 18.sp else 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 28.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = camp.ashaWorkerName,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = camp.location,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Today,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = formattedDate,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )
            }

            if (camp.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = camp.description,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Attend to get your free health checkup!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

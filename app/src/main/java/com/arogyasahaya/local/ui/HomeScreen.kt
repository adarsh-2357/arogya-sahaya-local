package com.arogyasahaya.local.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arogyasahaya.local.ui.medicine.MedicineViewModel
import com.arogyasahaya.local.ui.navigation.Screen
import com.arogyasahaya.local.ui.profile.MedicalProfileViewModel
import com.arogyasahaya.local.ui.vitals.VitalsViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToMedicines: () -> Unit,
    onNavigateToVitals: () -> Unit,
    onNavigateToAsha: () -> Unit,
    onNavigateToEmergency: () -> Unit,
    medicineViewModel: MedicineViewModel = hiltViewModel(),
    vitalsViewModel: VitalsViewModel = hiltViewModel(),
    profileViewModel: MedicalProfileViewModel = hiltViewModel()
) {
    val userName by profileViewModel.userName.collectAsState()
    val activeMedicines by medicineViewModel.activeMedicines.collectAsState()
    val latestLog by vitalsViewModel.latestLog.collectAsState()
    val activeMedicineCount by medicineViewModel.activeMedicineCount.collectAsState()

    val greeting = if (userName.isNotBlank()) "Namaste, $userName!" else "Namaste!"

    val dateFormat = SimpleDateFormat("EEE, d MMM", Locale.getDefault())
    val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())

    val nextMedicineTime = if (activeMedicines.isNotEmpty()) {
        val now = Date()
        val morningTime = Date(now.time + ((8 * 60 - now.hours * 60 - now.minutes) * 60 * 1000L))
        timeFormat.format(morningTime)
    } else {
        "No medicines scheduled"
    }

    val lastVitalDate = latestLog?.let {
        dateFormat.format(Date(it.date))
    } ?: "No vitals logged"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Arogya-Sahaya",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.semantics { heading() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Greeting Header
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = greeting,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.semantics { heading() },
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Your health companion",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Quick Stats Cards
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(
                        modifier = Modifier.weight(1f),
                        title = "Active Medicines",
                        value = activeMedicineCount.toString(),
                        subtitle = "scheduled",
                        icon = Icons.Default.LocalHospital,
                        iconTint = MaterialTheme.colorScheme.primary
                    )

                    StatCard(
                        modifier = Modifier.weight(1f),
                        title = "Next Medicine",
                        value = nextMedicineTime,
                        subtitle = "today",
                        icon = Icons.Default.Warning,
                        iconTint = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            item {
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
                        Column {
                            Text(
                                text = "Last Vital Log",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = lastVitalDate,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Vitals icon",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }

            // Navigation Cards Header
            item {
                Text(
                    text = "Quick Access",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.semantics { heading() }
                )
            }

            // 2x2 Navigation Grid
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    NavCard(
                        modifier = Modifier.weight(1f).height(120.dp),
                        label = "Medicines",
                        icon = Icons.Default.LocalHospital,
                        iconTint = Color(0xFF00897B),
                        onClick = onNavigateToMedicines
                    )

                    NavCard(
                        modifier = Modifier.weight(1f).height(120.dp),
                        label = "Vitals",
                        icon = Icons.Default.Favorite,
                        iconTint = Color(0xFFE65100),
                        onClick = onNavigateToVitals
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    NavCard(
                        modifier = Modifier.weight(1f).height(120.dp),
                        label = "ASHA Camps",
                        icon = Icons.Default.CalendarMonth,
                        iconTint = Color(0xFF1976D2),
                        onClick = onNavigateToAsha
                    )

                    NavCard(
                        modifier = Modifier.weight(1f).height(120.dp),
                        label = "Emergency",
                        icon = Icons.Default.Warning,
                        iconTint = Color(0xFFD32F2F),
                        onClick = onNavigateToEmergency
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$title icon",
                tint = iconTint,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun NavCard(
    modifier: Modifier = Modifier,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surface),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "$label navigation",
                    tint = iconTint,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

package com.arogyasahaya.local.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arogyasahaya.local.data.local.entity.VitalLogEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun VitalCard(
    vitalLog: VitalLogEntity,
    onDelete: (VitalLogEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("EEE, MMM d • h:mm a", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(vitalLog.date))

    val isHighBP = (vitalLog.bloodPressureSystolic ?: 0) > 140 ||
            (vitalLog.bloodPressureDiastolic ?: 0) > 90

    val bpColor = if (vitalLog.bloodPressureSystolic != null &&
        vitalLog.bloodPressureDiastolic != null
    ) {
        if (isHighBP) Color(0xFFD32F2F) else Color(0xFF388E3C)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formattedDate,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )

                IconButton(
                    onClick = { onDelete(vitalLog) },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                if (vitalLog.bloodPressureSystolic != null &&
                    vitalLog.bloodPressureDiastolic != null
                ) {
                    VitalMetric(
                        label = "Blood Pressure",
                        value = "${vitalLog.bloodPressureSystolic}/${vitalLog.bloodPressureDiastolic}",
                        unit = "mmHg",
                        valueColor = bpColor,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (vitalLog.heartRate != null) {
                    VitalMetric(
                        label = "Heart Rate",
                        value = vitalLog.heartRate.toString(),
                        unit = "bpm",
                        valueColor = Color(0xFF1976D2),
                        modifier = Modifier.weight(1f)
                    )
                }

                if (vitalLog.glucoseLevel != null) {
                    VitalMetric(
                        label = "Glucose",
                        value = String.format("%.1f", vitalLog.glucoseLevel),
                        unit = "mg/dL",
                        valueColor = Color(0xFFF57C00),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            if (vitalLog.notes.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = vitalLog.notes,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun VitalMetric(
    label: String,
    value: String,
    unit: String,
    valueColor: Color,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = valueColor
            )
            Text(
                text = unit,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

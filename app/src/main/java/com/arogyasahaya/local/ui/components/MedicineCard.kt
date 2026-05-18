package com.arogyasahaya.local.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arogyasahaya.local.data.local.entity.MedicineEntity

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MedicineCard(
    medicine: MedicineEntity,
    onDelete: (MedicineEntity) -> Unit,
    onToggleActive: (MedicineEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDelete(medicine)
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            val color by animateColorAsState(
                targetValue = when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.error
                    else -> Color.Transparent
                },
                label = "swipe_bg_color"
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(color)
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.onError,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = "Delete",
                            color = MaterialTheme.colorScheme.onError,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        },
        enableDismissFromStartToEnd = false,
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (medicine.isActive) {
                    MaterialTheme.colorScheme.surface
                } else {
                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                }
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = if (medicine.isActive) 2.dp else 0.dp
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = medicine.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (medicine.isActive) {
                            MaterialTheme.colorScheme.onSurface
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        },
                        textDecoration = if (medicine.isActive) {
                            TextDecoration.None
                        } else {
                            TextDecoration.LineThrough
                        }
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = medicine.dosage,
                        fontSize = 16.sp,
                        color = if (medicine.isActive) {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        medicine.timesOfDay.forEach { time ->
                            TimeChip(
                                label = time,
                                isActive = medicine.isActive
                            )
                        }
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconButton(
                        onClick = { onToggleActive(medicine) },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = if (medicine.isActive) {
                                Icons.Default.Notifications
                            } else {
                                Icons.Default.NotificationsOff
                            },
                            contentDescription = if (medicine.isActive) {
                                "Pause reminders"
                            } else {
                                "Resume reminders"
                            },
                            tint = if (medicine.isActive) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                            },
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    IconButton(
                        onClick = { onDelete(medicine) },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete medicine",
                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TimeChip(
    label: String,
    isActive: Boolean
) {
    val chipColor = when (label.lowercase()) {
        "morning" -> Color(0xFFFFF3E0) to Color(0xFFE65100)
        "afternoon" -> Color(0xFFFFFDE7) to Color(0xFFF57F17)
        "night" -> Color(0xFFE8EAF6) to Color(0xFF283593)
        else -> MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.onSecondaryContainer
    }

    val emoji = when (label.lowercase()) {
        "morning" -> "🌅"
        "afternoon" -> "☀️"
        "night" -> "🌙"
        else -> "💊"
    }

    val backgroundColor = if (isActive) chipColor.first else chipColor.first.copy(alpha = 0.3f)
    val textColor = if (isActive) chipColor.second else chipColor.second.copy(alpha = 0.4f)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = emoji,
                fontSize = 14.sp
            )
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )
        }
    }
}

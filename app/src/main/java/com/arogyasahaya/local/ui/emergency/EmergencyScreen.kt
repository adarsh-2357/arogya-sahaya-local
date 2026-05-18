package com.arogyasahaya.local.ui.emergency

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.arogyasahaya.local.ui.profile.MedicalProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyScreen(
    onNavigateBack: () -> Unit,
    viewModel: MedicalProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val emergencyContact by viewModel.emergencyContact.collectAsState()
    val emergencyContactName by viewModel.emergencyContactName.collectAsState()
    val userName by viewModel.userName.collectAsState()

    var showCallDialog by remember { mutableStateOf(false) }
    var showSmsDialog by remember { mutableStateOf(false) }
    var permissionRequired by remember { mutableStateOf(false) }

    val callPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            makePhoneCall(context, emergencyContact)
        } else {
            permissionRequired = true
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "sosPulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sosScale"
    )

    Scaffold(
        containerColor = Color(0xFFFFEBEE),
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {
                    Text(
                        text = "Emergency SOS",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC62828)
                    )
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.Default.Call,
                            contentDescription = "Back",
                            tint = Color(0xFFC62828)
                        )
                    }
                },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFEBEE)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFFEBEE))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Warning Header
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFCDD2)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFFC62828),
                        modifier = Modifier.size(32.dp)
                    )
                    Column {
                        Text(
                            text = "Emergency Services",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFC62828)
                        )
                        Text(
                            text = "Use only in genuine emergencies",
                            fontSize = 14.sp,
                            color = Color(0xFFC62828).copy(alpha = 0.8f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Giant SOS Button with Pulsing Animation
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale)
                    .background(
                        color = Color(0xFFD32F2F),
                        shape = CircleShape
                    )
                    .border(
                        width = 6.dp,
                        color = Color(0xFFB71C1C),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.material3.Button(
                    onClick = { showCallDialog = true },
                    modifier = Modifier
                        .size(190.dp)
                        .background(Color(0xFFD32F2F), CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F)
                    ),
                    shape = CircleShape
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "SOS",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "TAP HERE",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Emergency Contact Display
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Emergency Contact",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (emergencyContactName.isNotBlank()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = emergencyContactName,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    if (emergencyContact.isNotBlank()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = emergencyContact,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    if (emergencyContactName.isBlank() || emergencyContact.isBlank()) {
                        Text(
                            text = "No emergency contact set. Please update your profile.",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.error,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nearest PHC Info
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF1976D2),
                        modifier = Modifier.size(28.dp)
                    )
                    Column {
                        Text(
                            text = "Nearest PHC",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1565C0)
                        )
                        Text(
                            text = "Simulated — 2.3 km away",
                            fontSize = 16.sp,
                            color = Color(0xFF1565C0).copy(alpha = 0.8f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Quick Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { showCallDialog = true },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Icon(Icons.Default.Call, null, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Call", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }

                Button(
                    onClick = { showSmsDialog = true },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2196F3)
                    )
                ) {
                    Icon(Icons.Default.Message, null, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("SMS", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Call Confirmation Dialog
        if (showCallDialog && emergencyContact.isNotBlank()) {
            AlertDialog(
                onDismissRequest = { showCallDialog = false },
                icon = {
                    Icon(
                        Icons.Default.Phone,
                        contentDescription = null,
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(48.dp)
                    )
                },
                title = {
                    Text(
                        text = "Call Emergency Contact?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (emergencyContactName.isNotBlank()) {
                            Text(
                                text = "Contact: $emergencyContactName",
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "Number: $emergencyContact",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showCallDialog = false
                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CALL_PHONE
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                makePhoneCall(context, emergencyContact)
                            } else {
                                callPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text("Call Now", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showCallDialog = false }) {
                        Text("Cancel", fontSize = 16.sp)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
        }

        // SMS Confirmation Dialog
        if (showSmsDialog && emergencyContact.isNotBlank()) {
            val smsText = "I need help. This is ${if (userName.isNotBlank()) userName else "the user"}. Please contact me immediately."

            AlertDialog(
                onDismissRequest = { showSmsDialog = false },
                icon = {
                    Icon(
                        Icons.Default.Message,
                        contentDescription = null,
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(48.dp)
                    )
                },
                title = {
                    Text(
                        text = "Send Emergency SMS?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = "To: $emergencyContact",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF5F5F5)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = smsText,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showSmsDialog = false
                            sendEmergencySMS(context, emergencyContact, smsText)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3)
                        )
                    ) {
                        Text("Send SMS", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showSmsDialog = false }) {
                        Text("Cancel", fontSize = 16.sp)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
        }

        // Permission Required Dialog
        if (permissionRequired) {
            AlertDialog(
                onDismissRequest = { permissionRequired = false },
                title = { Text("Permission Required", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                text = { Text("Please grant phone call permission in Settings to make emergency calls.", fontSize = 16.sp) },
                confirmButton = {
                    Button(onClick = { permissionRequired = false }) {
                        Text("OK", fontSize = 16.sp)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}

private fun makePhoneCall(context: android.content.Context, phoneNumber: String) {
    try {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun sendEmergencySMS(context: android.content.Context, phoneNumber: String, message: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("sms:$phoneNumber")
            putExtra("sms_body", message)
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

package com.arogyasahaya.local.ui.vitals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arogyasahaya.local.ui.components.VitalCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalLogScreen(
    onNavigateBack: () -> Unit,
    onNavigateToChart: () -> Unit,
    viewModel: VitalsViewModel = hiltViewModel()
) {
    var systolic by remember { mutableStateOf("") }
    var diastolic by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }
    var glucose by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    var systolicError by remember { mutableStateOf(false) }
    var diastolicError by remember { mutableStateOf(false) }
    var heartRateError by remember { mutableStateOf(false) }
    var glucoseError by remember { mutableStateOf(false) }

    val allLogs by viewModel.allLogs.collectAsState()
    val logSuccess by viewModel.logSuccess.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(logSuccess) {
        if (logSuccess) {
            snackbarHostState.showSnackbar("Vitals logged successfully!")
            viewModel.resetLogSuccess()
            systolic = ""
            diastolic = ""
            heartRate = ""
            glucose = ""
            notes = ""
        }
    }

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    val highContrastColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        cursorColor = MaterialTheme.colorScheme.primary,
        errorBorderColor = MaterialTheme.colorScheme.error,
        errorLabelColor = MaterialTheme.colorScheme.error
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Log Vitals",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
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
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Track Your Health",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Log your vital signs for today",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Systolic BP (mmHg)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = systolic,
                    onValueChange = {
                        systolic = it.filter { c -> c.isDigit() }
                        if (it.isNotEmpty()) systolicError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 20.sp),
                    placeholder = {
                        Text("e.g., 120", fontSize = 18.sp)
                    },
                    isError = systolicError,
                    supportingText = if (systolicError) {
                        { Text("Enter a valid number (70-250)", fontSize = 14.sp) }
                    } else null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = highContrastColors,
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Diastolic BP (mmHg)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = diastolic,
                    onValueChange = {
                        diastolic = it.filter { c -> c.isDigit() }
                        if (it.isNotEmpty()) diastolicError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 20.sp),
                    placeholder = {
                        Text("e.g., 80", fontSize = 18.sp)
                    },
                    isError = diastolicError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = highContrastColors,
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Heart Rate (bpm)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = heartRate,
                    onValueChange = {
                        heartRate = it.filter { c -> c.isDigit() }
                        if (it.isNotEmpty()) heartRateError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 20.sp),
                    placeholder = {
                        Text("e.g., 72", fontSize = 18.sp)
                    },
                    isError = heartRateError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = highContrastColors,
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Glucose Level (mg/dL)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = glucose,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.all { it.isDigit() || it == '.' }) {
                            if (newValue.count { it == '.' } > 1) return@OutlinedTextField
                            glucose = newValue
                            if (newValue.isNotEmpty()) glucoseError = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 20.sp),
                    placeholder = {
                        Text("e.g., 95.5", fontSize = 18.sp)
                    },
                    isError = glucoseError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors = highContrastColors,
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Notes (Optional)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    textStyle = TextStyle(fontSize = 18.sp),
                    placeholder = {
                        Text("e.g., Felt dizzy after measurement", fontSize = 16.sp)
                    },
                    colors = highContrastColors,
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 4
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val sys = systolic.toIntOrNull()
                    val dia = diastolic.toIntOrNull()
                    val hr = heartRate.toIntOrNull()
                    val glu = glucose.toFloatOrNull()

                    systolicError = systolic.isNotEmpty() && sys == null
                    diastolicError = diastolic.isNotEmpty() && dia == null
                    heartRateError = heartRate.isNotEmpty() && hr == null
                    glucoseError = glucose.isNotEmpty() && glu == null

                    if (sys != null && dia != null && (sys > 250 || dia > 150)) {
                        systolicError = true
                        diastolicError = true
                        return@Button
                    }

                    viewModel.logVital(
                        systolic = sys,
                        diastolic = dia,
                        heartRate = hr,
                        glucose = glu,
                        notes = notes
                    )
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(28.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 3.dp
                    )
                } else {
                    Icon(Icons.Default.Check, null, Modifier.size(28.dp))
                    Spacer(Modifier.width(12.dp))
                    Text("Log Today's Vitals", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Entries",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                if (allLogs.isNotEmpty()) {
                    TextButton(onClick = onNavigateToChart) {
                        Text("View 7-Day Chart", fontSize = 16.sp)
                    }
                }
            }

            if (allLogs.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No vitals logged yet.\nStart by logging today's readings!",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 24.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items = allLogs.sortedByDescending { it.date }, key = { it.id }) { log ->
                        VitalCard(
                            vitalLog = log,
                            onDelete = { viewModel.deleteVitalLog(it) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

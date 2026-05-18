package com.arogyasahaya.local.ui.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

data class TimeOption(
    val label: String,
    val emoji: String,
    val description: String,
    val selectedBg: Color,
    val selectedText: Color
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddMedicineScreen(
    onNavigateBack: () -> Unit,
    viewModel: MedicineViewModel = hiltViewModel()
) {
    var medicineName by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    val selectedTimes = remember { mutableStateListOf<String>() }

    var nameError by remember { mutableStateOf(false) }
    var dosageError by remember { mutableStateOf(false) }
    var timeError by remember { mutableStateOf(false) }

    val addSuccess by viewModel.addMedicineSuccess.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val timeOptions = listOf(
        TimeOption(
            label = "Morning",
            emoji = "🌅",
            description = "8:00 AM",
            selectedBg = Color(0xFFFFF3E0),
            selectedText = Color(0xFFE65100)
        ),
        TimeOption(
            label = "Afternoon",
            emoji = "☀️",
            description = "2:00 PM",
            selectedBg = Color(0xFFFFFDE7),
            selectedText = Color(0xFFF57F17)
        ),
        TimeOption(
            label = "Night",
            emoji = "🌙",
            description = "9:00 PM",
            selectedBg = Color(0xFFE8EAF6),
            selectedText = Color(0xFF283593)
        )
    )

    LaunchedEffect(addSuccess) {
        if (addSuccess) {
            viewModel.resetAddMedicineSuccess()
            onNavigateBack()
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
                        text = "Add Medicine",
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
            verticalArrangement = Arrangement.spacedBy(24.dp)
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
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "💊",
                        fontSize = 36.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "New Medicine",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Fill in the details below",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Medicine Name *",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = medicineName,
                    onValueChange = {
                        medicineName = it
                        if (it.isNotBlank()) nameError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 20.sp),
                    placeholder = {
                        Text(
                            text = "e.g., Metformin, Amlodipine",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    isError = nameError,
                    supportingText = if (nameError) {
                        { Text("Medicine name is required", fontSize = 14.sp) }
                    } else {
                        null
                    },
                    colors = highContrastColors,
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Dosage *",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = dosage,
                    onValueChange = {
                        dosage = it
                        if (it.isNotBlank()) dosageError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 20.sp),
                    placeholder = {
                        Text(
                            text = "e.g., 1 tablet, 5 ml syrup",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    isError = dosageError,
                    supportingText = if (dosageError) {
                        { Text("Dosage is required", fontSize = 14.sp) }
                    } else {
                        null
                    },
                    colors = highContrastColors,
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "When to take *",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                if (timeError) {
                    Text(
                        text = "Select at least one time",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Medium
                    )
                }

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    timeOptions.forEach { option ->
                        val isSelected = option.label in selectedTimes

                        TimeToggleChip(
                            option = option,
                            isSelected = isSelected,
                            onClick = {
                                if (isSelected) {
                                    selectedTimes.remove(option.label)
                                } else {
                                    selectedTimes.add(option.label)
                                    timeError = false
                                }
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val isNameValid = medicineName.isNotBlank()
                    val isDosageValid = dosage.isNotBlank()
                    val isTimeValid = selectedTimes.isNotEmpty()

                    nameError = !isNameValid
                    dosageError = !isDosageValid
                    timeError = !isTimeValid

                    if (isNameValid && isDosageValid && isTimeValid) {
                        viewModel.addMedicine(
                            name = medicineName,
                            dosage = dosage,
                            timesOfDay = selectedTimes.toList()
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
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
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Save Medicine",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TimeToggleChip(
    option: TimeOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        option.selectedBg
    } else {
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    }

    val borderColor = if (isSelected) {
        option.selectedText.copy(alpha = 0.5f)
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
    }

    val textColor = if (isSelected) {
        option.selectedText
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = option.emoji,
                fontSize = 28.sp
            )
            Text(
                text = option.label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Text(
                text = option.description,
                fontSize = 14.sp,
                color = textColor.copy(alpha = 0.7f)
            )
        }
    }
}

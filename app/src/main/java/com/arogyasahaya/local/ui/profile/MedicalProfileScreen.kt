package com.arogyasahaya.local.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalProfileScreen(
    onNavigateBack: () -> Unit,
    viewModel: MedicalProfileViewModel = hiltViewModel()
) {
    val savedUserName by viewModel.userName.collectAsState()
    val savedAge by viewModel.age.collectAsState()
    val savedBloodGroup by viewModel.bloodGroup.collectAsState()
    val savedChronicConditions by viewModel.chronicConditions.collectAsState()
    val savedEmergencyContact by viewModel.emergencyContact.collectAsState()
    val savedEmergencyContactName by viewModel.emergencyContactName.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var userName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var chronicConditions by remember { mutableStateOf("") }
    var emergencyContact by remember { mutableStateOf("") }
    var emergencyContactName by remember { mutableStateOf("") }
    var bloodGroupExpanded by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")

    LaunchedEffect(savedUserName, savedAge, savedBloodGroup, savedChronicConditions, savedEmergencyContact, savedEmergencyContactName) {
        userName = savedUserName
        age = if (savedAge > 0) savedAge.toString() else ""
        bloodGroup = savedBloodGroup
        chronicConditions = savedChronicConditions
        emergencyContact = savedEmergencyContact
        emergencyContactName = savedEmergencyContactName
    }

    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            snackbarHostState.showSnackbar("Profile saved successfully!")
            viewModel.resetSaveSuccess()
        }
    }

    val highContrastColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        cursorColor = MaterialTheme.colorScheme.primary
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Medical Profile",
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
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionColor = MaterialTheme.colorScheme.primary
                )
            }
        }
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Personal Information",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Keep your medical details updated",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }
            }

            Text(
                text = "Full Name",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 20.sp),
                placeholder = {
                    Text(
                        text = "Enter your full name",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                colors = highContrastColors,
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Text(
                text = "Age",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedTextField(
                value = age,
                onValueChange = { newValue ->
                    if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                        age = newValue
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 20.sp),
                placeholder = {
                    Text(
                        text = "Enter your age",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = highContrastColors,
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Text(
                text = "Blood Group",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = bloodGroup,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { bloodGroupExpanded = true },
                    textStyle = TextStyle(fontSize = 20.sp),
                    placeholder = {
                        Text(
                            text = "Select blood group",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    readOnly = true,
                    enabled = false,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Select",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable { bloodGroupExpanded = true },
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                DropdownMenu(
                    expanded = bloodGroupExpanded,
                    onDismissRequest = { bloodGroupExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    bloodGroups.forEach { group ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = group,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            },
                            onClick = {
                                bloodGroup = group
                                bloodGroupExpanded = false
                            },
                            trailingIcon = {
                                if (bloodGroup == group) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Selected",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }

            Text(
                text = "Chronic Conditions",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedTextField(
                value = chronicConditions,
                onValueChange = { chronicConditions = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                textStyle = TextStyle(fontSize = 20.sp),
                placeholder = {
                    Text(
                        text = "e.g., Diabetes, Hypertension, Asthma...",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                colors = highContrastColors,
                shape = RoundedCornerShape(12.dp),
                maxLines = 5
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Emergency Contact",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                text = "Emergency Contact Name",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedTextField(
                value = emergencyContactName,
                onValueChange = { emergencyContactName = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 20.sp),
                placeholder = {
                    Text(
                        text = "Enter contact person's name",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                colors = highContrastColors,
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Text(
                text = "Emergency Contact Number",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedTextField(
                value = emergencyContact,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() || it == '+' || it == '-' || it == ' ' }) {
                        emergencyContact = newValue
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 20.sp),
                placeholder = {
                    Text(
                        text = "Enter phone number",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                colors = highContrastColors,
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    viewModel.saveAllProfile(
                        userName = userName,
                        age = age.toIntOrNull() ?: 0,
                        bloodGroup = bloodGroup,
                        chronicConditions = chronicConditions,
                        emergencyContact = emergencyContact,
                        emergencyContactName = emergencyContactName
                    )
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
                        text = "Save Profile",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

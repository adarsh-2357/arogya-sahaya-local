import { useState } from "react";

interface FileItem {
  name: string;
  path: string;
  content: string;
  tag: string;
}

const files: FileItem[] = [
  {
    name: "EmergencyScreen.kt",
    path: "ui/emergency/EmergencyScreen.kt",
    tag: "SCREEN",
    content: `package com.arogyasahaya.local.ui.emergency

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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.arogyasahaya.local.ui.profile.MedicalProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyScreen(onNavigateBack: () -> Unit, viewModel: MedicalProfileViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val emergencyContact by viewModel.emergencyContact.collectAsState()
    val emergencyContactName by viewModel.emergencyContactName.collectAsState()
    val userName by viewModel.userName.collectAsState()

    var showCallDialog by remember { mutableStateOf(false) }
    var showSmsDialog by remember { mutableStateOf(false) }

    val callPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted -> if (isGranted) makePhoneCall(context, emergencyContact) }

    // Pulsing animation: scale 1.0 → 1.05 → 1.0, 1 second loop
    val infiniteTransition = rememberInfiniteTransition(label = "sosPulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f, targetValue = 1.05f,
        animationSpec = infiniteRepeatable(animation = tween(500), repeatMode = RepeatMode.Reverse),
        label = "sosScale"
    )

    Scaffold(containerColor = Color(0xFFFFEBEE),
        topBar = {
            TopAppBar(title = { Text("Emergency SOS", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC62828)) },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.Default.Call, "Back", tint = Color(0xFFC62828)) } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFEBEE))
            )
        }
    ) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding).background(Color(0xFFFFEBEE)).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            // Warning Header
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCDD2)), shape = RoundedCornerShape(16.dp)) {
                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Icon(Icons.Default.LocationOn, null, tint = Color(0xFFC62828), modifier = Modifier.size(32.dp))
                    Column {
                        Text("Emergency Services", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC62828))
                        Text("Use only in genuine emergencies", fontSize = 14.sp, color = Color(0xFFC62828).copy(alpha = 0.8f))
                    }
                }
            }
            // Giant 200dp SOS Button with pulsing animation
            Box(modifier = Modifier.size(200.dp).scale(scale)
                .background(Color(0xFFD32F2F), CircleShape)
                .border(6.dp, Color(0xFFB71C1C), CircleShape), contentAlignment = Alignment.Center) {
                Button(onClick = { showCallDialog = true }, modifier = Modifier.size(190.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)), shape = CircleShape) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("SOS", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text("TAP HERE", fontSize = 14.sp, color = Color.White.copy(alpha = 0.9f))
                    }
                }
            }
            // Emergency Contact Display
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                 elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Emergency Contact", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    if (emergencyContactName.isNotBlank()) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Icon(Icons.Default.Person, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
                            Text(emergencyContactName, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                    if (emergencyContact.isNotBlank()) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Icon(Icons.Default.Phone, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
                            Text(emergencyContact, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
            // Nearest PHC Info
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)), shape = RoundedCornerShape(16.dp)) {
                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Icon(Icons.Default.LocationOn, null, tint = Color(0xFF1976D2), modifier = Modifier.size(28.dp))
                    Column {
                        Text("Nearest PHC", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1565C0))
                        Text("Simulated — 2.3 km away", fontSize = 16.sp, color = Color(0xFF1565C0).copy(alpha = 0.8f))
                    }
                }
            }
            // Quick Action Buttons
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { showCallDialog = true }, modifier = Modifier.weight(1f).height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))) {
                    Icon(Icons.Default.Call, null, modifier = Modifier.size(24.dp)); Spacer(Modifier.width(8.dp)); Text("Call", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
                Button(onClick = { showSmsDialog = true }, modifier = Modifier.weight(1f).height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))) {
                    Icon(Icons.Default.Message, null, modifier = Modifier.size(24.dp)); Spacer(Modifier.width(8.dp)); Text("SMS", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
        // Call Dialog
        if (showCallDialog && emergencyContact.isNotBlank()) {
            AlertDialog(onDismissRequest = { showCallDialog = false },
                icon = { Icon(Icons.Default.Phone, null, tint = Color(0xFFD32F2F), modifier = Modifier.size(48.dp)) },
                title = { Text("Call Emergency Contact?", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (emergencyContactName.isNotBlank()) Text("Contact: \$emergencyContactName", fontSize = 18.sp)
                        Text("Number: \$emergencyContact", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.primary)
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        showCallDialog = false
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                            makePhoneCall(context, emergencyContact)
                        else callPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))) { Text("Call Now", fontSize = 16.sp, fontWeight = FontWeight.SemiBold) }
                },
                dismissButton = { TextButton(onClick = { showCallDialog = false }) { Text("Cancel") } }
            )
        }
        // SMS Dialog
        if (showSmsDialog && emergencyContact.isNotBlank()) {
            val smsText = "I need help. This is \${if (userName.isNotBlank()) userName else "the user"}. Please contact me immediately."
            AlertDialog(onDismissRequest = { showSmsDialog = false },
                icon = { Icon(Icons.Default.Message, null, tint = Color(0xFF2196F3), modifier = Modifier.size(48.dp)) },
                title = { Text("Send Emergency SMS?", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("To: \$emergencyContact", fontSize = 16.sp)
                        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), shape = RoundedCornerShape(8.dp)) {
                            Text(smsText, fontSize = 16.sp, modifier = Modifier.padding(12.dp))
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { showSmsDialog = false; sendEmergencySMS(context, emergencyContact, smsText) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))) { Text("Send SMS", fontSize = 16.sp, fontWeight = FontWeight.SemiBold) }
                },
                dismissButton = { TextButton(onClick = { showSmsDialog = false }) { Text("Cancel") } }
            )
        }
    }
}

private fun makePhoneCall(context: android.content.Context, phoneNumber: String) {
    try { context.startActivity(Intent(Intent.ACTION_CALL).apply { data = Uri.parse("tel:\$phoneNumber") }) }
    catch (e: Exception) { e.printStackTrace() }
}

private fun sendEmergencySMS(context: android.content.Context, phoneNumber: String, message: String) {
    try { context.startActivity(Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("sms:\$phoneNumber"); putExtra("sms_body", message)
    }) } catch (e: Exception) { e.printStackTrace() }
}`,
  },
  {
    name: "NavGraph.kt",
    path: "ui/navigation/NavGraph.kt",
    tag: "NAV",
    content: `package com.arogyasahaya.local.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arogyasahaya.local.ui.asha.AshaConnectScreen
import com.arogyasahaya.local.ui.emergency.EmergencyScreen
import com.arogyasahaya.local.ui.medicine.AddMedicineScreen
import com.arogyasahaya.local.ui.medicine.MedicineScreen
import com.arogyasahaya.local.ui.profile.MedicalProfileScreen
import com.arogyasahaya.local.ui.vitals.VitalLogScreen
import com.arogyasahaya.local.ui.vitals.VitalsChartScreen

// Sealed class for type-safe navigation
sealed class Screen(val route: String) {
    data object Profile : Screen("profile")
    data object Medicines : Screen("medicines")
    data object AddMedicine : Screen("add_medicine")
    data object Vitals : Screen("vitals")
    data object VitalsChart : Screen("vitals_chart")
    data object AshaConnect : Screen("asha_connect")
    data object Emergency : Screen("emergency")
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier, startDestination: String = Screen.Profile.route) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(Screen.Profile.route) {
            MedicalProfileScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.Medicines.route) {
            MedicineScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddMedicine = { navController.navigate(Screen.AddMedicine.route) }
            )
        }
        composable(Screen.AddMedicine.route) {
            AddMedicineScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.Vitals.route) {
            VitalLogScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToChart = { navController.navigate(Screen.VitalsChart.route) }
            )
        }
        composable(Screen.VitalsChart.route) {
            VitalsChartScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.AshaConnect.route) {
            AshaConnectScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.Emergency.route) {
            EmergencyScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}`,
  },
  {
    name: "BottomNavBar.kt",
    path: "ui/components/BottomNavBar.kt",
    tag: "COMPONENT",
    content: `package com.arogyasahaya.local.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arogyasahaya.local.ui.navigation.Screen

@Composable
fun BottomNavBar(navController: NavController, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val mainScreens = listOf(
        BottomNavItem.Profile,
        BottomNavItem.Medicines,
        BottomNavItem.Vitals,
        BottomNavItem.AshaConnect,
        BottomNavItem.Emergency
    )

    Box(modifier = modifier.fillMaxWidth().height(80.dp).background(MaterialTheme.colorScheme.surface)) {
        Row(modifier = Modifier.fillMaxWidth().selectableGroup(), verticalAlignment = Alignment.CenterVertically) {
            mainScreens.forEach { screen ->
                BottomNavItem(
                    screen = screen,
                    isSelected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun RowScope.BottomNavItem(screen: BottomNavItem, isSelected: Boolean, onClick: () -> Unit) {
    val tintColor = if (screen == BottomNavItem.Emergency) {
        if (isSelected) Color.White else Color(0xFFD32F2F)
    } else {
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
    }

    val backgroundColor = if (screen == BottomNavItem.Emergency && isSelected) {
        Color(0xFFD32F2F)
    } else Color.Transparent

    Box(modifier = Modifier.weight(1f).height(80.dp).background(backgroundColor)
        .selectable(selected = isSelected, onClick = onClick, role = Role.Tab),
        contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            // Large 28dp icons
            Icon(imageVector = screen.icon, contentDescription = screen.label, tint = tintColor, modifier = Modifier.size(28.dp))
            // Large 14sp label text
            Text(text = screen.label, fontSize = 14.sp, fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                 color = tintColor, textAlign = TextAlign.Center, maxLines = 1)
        }
    }
}

sealed class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    data object Profile : BottomNavItem(Screen.Profile.route, "Profile", Icons.Default.Home)
    data object Medicines : BottomNavItem(Screen.Medicines.route, "Medicines", Icons.Default.LocalHospital)
    data object Vitals : BottomNavItem(Screen.Vitals.route, "Vitals", Icons.Default.Favorite)
    data object AshaConnect : BottomNavItem(Screen.AshaConnect.route, "ASHA", Icons.Default.CalendarMonth)
    data object Emergency : BottomNavItem(Screen.Emergency.route, "SOS", Icons.Default.Warning)
}`,
  },
  {
    name: "MainActivity.kt",
    path: "ui/MainActivity.kt",
    tag: "ACTIVITY",
    content: `package com.arogyasahaya.local.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arogyasahaya.local.ui.components.BottomNavBar
import com.arogyasahaya.local.ui.navigation.NavGraph
import com.arogyasahaya.local.ui.navigation.Screen
import com.arogyasahaya.local.ui.theme.ArogyaSahayaLocalTheme
import com.arogyasahaya.local.util.NotificationHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var notificationHelper: NotificationHelper

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> if (isGranted) notificationHelper.createNotificationChannel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestNotificationPermission()

        setContent {
            ArogyaSahayaLocalTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainAppContent()
                }
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                notificationHelper.createNotificationChannel()
            }
        } else {
            notificationHelper.createNotificationChannel()
        }
    }
}

@Composable
fun MainAppContent() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Show bottom bar only on main screens (not on detail screens like AddMedicine, VitalsChart)
    val showBottomBar = currentRoute in listOf(
        Screen.Profile.route, Screen.Medicines.route, Screen.Vitals.route,
        Screen.AshaConnect.route, Screen.Emergency.route
    )

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { if (showBottomBar) BottomNavBar(navController = navController) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        NavGraph(navController = navController, modifier = Modifier.padding(innerPadding), startDestination = Screen.Profile.route)
    }
}`,
  },
];

export default function App() {
  const [selectedIndex, setSelectedIndex] = useState(0);
  const [copied, setCopied] = useState(false);
  const sel = files[selectedIndex];

  const handleCopy = async () => {
    await navigator.clipboard.writeText(sel.content);
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  };

  const lines = sel.content.split("\n").length;

  const tagColor: Record<string, string> = {
    SCREEN: "bg-red-500/20 text-red-300 border-red-500/30",
    NAV: "bg-purple-500/20 text-purple-300 border-purple-500/30",
    COMPONENT: "bg-cyan-500/20 text-cyan-300 border-cyan-500/30",
    ACTIVITY: "bg-emerald-500/20 text-emerald-300 border-emerald-500/30",
  };

  return (
    <div className="min-h-screen bg-slate-900 text-white">
      <header className="border-b border-slate-700/50 bg-slate-900/80 px-6 py-4">
        <div className="flex items-center gap-3">
          <div className="flex h-10 w-10 items-center justify-center rounded-xl bg-gradient-to-br from-red-500 to-orange-600 text-xl">🚨</div>
          <div>
            <h1 className="text-xl font-bold">Arogya-Sahaya Local — Emergency SOS & Navigation</h1>
            <p className="text-sm text-slate-400">Pulsing SOS Button • 5-Tab Bottom Nav • Type-Safe Routes • Permission Handling</p>
          </div>
        </div>
      </header>

      <div className="flex">
        <aside className="w-80 shrink-0 border-r border-slate-700/50 bg-slate-900 h-[calc(100vh-73px)] overflow-y-auto">
          <div className="p-4">
            <h2 className="mb-3 text-xs font-semibold uppercase tracking-wider text-slate-500">Files ({files.length})</h2>
            <div className="space-y-1">
              {files.map((file, i) => (
                <button
                  key={file.path}
                  onClick={() => setSelectedIndex(i)}
                  className={`w-full rounded-lg px-3 py-2 text-left text-sm transition-all ${
                    selectedIndex === i
                      ? "bg-red-500/20 text-red-300 ring-1 ring-red-500/30"
                      : "text-slate-400 hover:bg-slate-800 hover:text-slate-200"
                  }`}
                >
                  <div className="font-medium truncate flex items-center gap-2">
                    <span className={`shrink-0 rounded px-1.5 py-0.5 text-[10px] font-bold border ${tagColor[file.tag]}`}>
                      {file.tag}
                    </span>
                    {file.name}
                  </div>
                  <div className="text-xs text-slate-500 truncate mt-0.5">{file.path}</div>
                </button>
              ))}
            </div>

            <div className="mt-6 space-y-3">
              <h3 className="text-xs font-semibold uppercase tracking-wider text-slate-500">Emergency Screen</h3>
              <div className="space-y-2 text-xs text-slate-400">
                <div className="flex items-start gap-2">
                  <span className="text-red-400 mt-0.5">✓</span>
                  <span>200dp circular SOS button with pulsing animation</span>
                </div>
                <div className="flex items-start gap-2">
                  <span className="text-red-400 mt-0.5">✓</span>
                  <span>AlertDialog for Call confirmation with permission</span>
                </div>
                <div className="flex items-start gap-2">
                  <span className="text-red-400 mt-0.5">✓</span>
                  <span>SMS option with pre-filled emergency text</span>
                </div>
                <div className="flex items-start gap-2">
                  <span className="text-red-400 mt-0.5">✓</span>
                  <span>Emergency contact display (name + number)</span>
                </div>
                <div className="flex items-start gap-2">
                  <span className="text-red-400 mt-0.5">✓</span>
                  <span>Nearest PHC info row (simulated 2.3 km)</span>
                </div>
              </div>
            </div>

            <div className="mt-6 space-y-3">
              <h3 className="text-xs font-semibold uppercase tracking-wider text-slate-500">Navigation</h3>
              <div className="space-y-2 text-xs text-slate-400">
                <div className="flex items-start gap-2">
                  <span className="text-purple-400 mt-0.5">✓</span>
                  <span>Sealed class Screen routes (type-safe)</span>
                </div>
                <div className="flex items-start gap-2">
                  <span className="text-purple-400 mt-0.5">✓</span>
                  <span>5-tab BottomNavBar with 28dp icons, 14sp labels</span>
                </div>
                <div className="flex items-start gap-2">
                  <span className="text-purple-400 mt-0.5">✓</span>
                  <span>SOS tab: red tinted when selected</span>
                </div>
                <div className="flex items-start gap-2">
                  <span className="text-purple-400 mt-0.5">✓</span>
                  <span>NavHost with Scaffold + conditional bottom bar</span>
                </div>
              </div>
            </div>

            <div className="mt-6 space-y-2">
              <h3 className="text-xs font-semibold uppercase tracking-wider text-slate-500">MainActivity</h3>
              <div className="space-y-1 text-xs text-slate-500">
                <div>• @AndroidEntryPoint for Hilt</div>
                <div>• POST_NOTIFICATIONS permission on Android 13+</div>
                <div>• NotificationHelper channel creation on start</div>
                <div>• Scaffold with conditional BottomNavBar</div>
              </div>
            </div>
          </div>
        </aside>

        <main className="flex-1 p-6 h-[calc(100vh-73px)] overflow-y-auto">
          <div className="rounded-xl border border-slate-700/50 bg-slate-800/50 overflow-hidden">
            <div className="flex items-center justify-between border-b border-slate-700/50 bg-slate-800 px-4 py-3">
              <div className="flex items-center gap-3">
                <div>
                  <h2 className="font-semibold text-white">{sel.name}</h2>
                  <p className="text-xs text-slate-400">app/src/main/java/com/arogyasahaya/local/{sel.path}</p>
                </div>
                <span className={`rounded-full border px-2 py-0.5 text-xs font-medium ${tagColor[sel.tag]}`}>
                  {sel.tag}
                </span>
                <span className="text-xs text-slate-500">{lines} lines</span>
              </div>
              <button
                onClick={handleCopy}
                className={`rounded-lg px-4 py-1.5 text-sm font-medium transition-all ${
                  copied ? "bg-emerald-500/20 text-emerald-400" : "bg-slate-700 text-slate-300 hover:bg-slate-600"
                }`}
              >
                {copied ? "✓ Copied!" : "Copy"}
              </button>
            </div>
            <pre className="overflow-auto p-4 text-sm leading-relaxed max-h-[calc(100vh-280px)]">
              <code className="text-slate-300 font-mono whitespace-pre">{sel.content}</code>
            </pre>
          </div>

          <div className="mt-6 grid grid-cols-2 gap-4">
            <div className="rounded-xl border border-slate-700/50 bg-slate-800/50 p-4">
              <div className="text-lg font-bold text-red-400">EmergencyScreen</div>
              <div className="text-xs text-slate-500 mt-1">
                200dp pulsing SOS button, Call/SMS AlertDialogs, emergency contact display, PHC info
              </div>
            </div>
            <div className="rounded-xl border border-slate-700/50 bg-slate-800/50 p-4">
              <div className="text-lg font-bold text-purple-400">NavGraph + BottomNavBar</div>
              <div className="text-xs text-slate-500 mt-1">
                Sealed class routes, 5 tabs (Profile/Medicines/Vitals/ASHA/SOS), red SOS tab, 28dp icons
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}

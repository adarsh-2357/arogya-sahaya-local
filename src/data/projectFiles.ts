export interface ProjectFile {
  id: string;
  name: string;
  path: string;
  language: string;
  category: "gradle" | "manifest" | "kotlin";
  icon: string;
  description: string;
  content: string;
}

export const projectFiles: ProjectFile[] = [
  {
    id: "settings-gradle",
    name: "settings.gradle.kts",
    path: "settings.gradle.kts",
    language: "kotlin",
    category: "gradle",
    icon: "⚙️",
    description: "Root settings — plugin management, dependency resolution, JitPack repo",
    content: `pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\\\.android.*")
                includeGroupByRegex("com\\\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Arogya-Sahaya-Local"
include(":app")`,
  },
  {
    id: "root-build-gradle",
    name: "build.gradle.kts",
    path: "build.gradle.kts",
    language: "kotlin",
    category: "gradle",
    icon: "🏗️",
    description: "Project-level build — AGP 8.2, Kotlin 1.9.22, KSP 1.9.22, Hilt 2.48.1",
    content: `plugins {
    id("com.android.application") version "8.2.2" apply false
    id("com.android.library") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}`,
  },
  {
    id: "app-build-gradle",
    name: "app/build.gradle.kts",
    path: "app/build.gradle.kts",
    language: "kotlin",
    category: "gradle",
    icon: "📦",
    description:
      "App-level build — Compose BOM, Room 2.6, Hilt 2.48, WorkManager 2.9, MPAndroidChart, DataStore",
    content: `plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.arogyasahaya.local"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arogyasahaya.local"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // ── Jetpack Compose BOM ─────────────────────────────────────────────
    val composeBom = platform("androidx.compose:compose-bom:2024.02.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // ── Compose Navigation ──────────────────────────────────────────────
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ── Lifecycle ───────────────────────────────────────────────────────
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // ── Activity Compose ────────────────────────────────────────────────
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.core:core-ktx:1.12.0")

    // ── Room 2.6 ────────────────────────────────────────────────────────
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:\$roomVersion")
    implementation("androidx.room:room-ktx:\$roomVersion")
    ksp("androidx.room:room-compiler:\$roomVersion")

    // ── Hilt 2.48 ───────────────────────────────────────────────────────
    val hiltVersion = "2.48.1"
    implementation("com.google.dagger:hilt-android:\$hiltVersion")
    ksp("com.google.dagger:hilt-compiler:\$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // ── WorkManager 2.9 ─────────────────────────────────────────────────
    implementation("androidx.work:work-runtime-ktx:2.9.0")

    // ── MPAndroidChart (via JitPack) ────────────────────────────────────
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // ── DataStore Preferences ───────────────────────────────────────────
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // ── Coroutines ──────────────────────────────────────────────────────
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // ── Testing ─────────────────────────────────────────────────────────
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}`,
  },
  {
    id: "android-manifest",
    name: "AndroidManifest.xml",
    path: "app/src/main/AndroidManifest.xml",
    language: "xml",
    category: "manifest",
    icon: "📋",
    description:
      "Manifest — permissions, HiltApplication, MainActivity, BootReceiver",
    content: `<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <!-- Permissions -->
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
    <uses-permission android:name="android.permission.CALL_PHONE" />
    <uses-permission android:name="android.permission.SEND_SMS" />

    <application
        android:name=".ArogyaApplication"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.ArogyaSahayaLocal"
        tools:targetApi="34">

        <!-- Main Activity -->
        <activity
            android:name=".ui.MainActivity"
            android:exported="true"
            android:theme="@style/Theme.ArogyaSahayaLocal">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- Boot Receiver -->
        <receiver
            android:name=".receiver.BootReceiver"
            android:exported="true"
            android:enabled="true">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
            </intent-filter>
        </receiver>

    </application>

</manifest>`,
  },
  {
    id: "arogya-application",
    name: "ArogyaApplication.kt",
    path: "app/src/main/java/com/arogyasahaya/local/ArogyaApplication.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "🏥",
    description: "Application class — @HiltAndroidApp entry point",
    content: `package com.arogyasahaya.local

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArogyaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}`,
  },
  {
    id: "main-activity",
    name: "MainActivity.kt",
    path: "app/src/main/java/com/arogyasahaya/local/ui/MainActivity.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "📱",
    description: "Main Activity — @AndroidEntryPoint, Compose setContent",
    content: `package com.arogyasahaya.local.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arogyasahaya.local.ui.theme.ArogyaSahayaLocalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArogyaSahayaLocalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArogyaNavHost()
                }
            }
        }
    }
}`,
  },
  {
    id: "nav-host",
    name: "ArogyaNavHost.kt",
    path: "app/src/main/java/com/arogyasahaya/local/ui/ArogyaNavHost.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "🧭",
    description: "Navigation host — Compose NavHost with route scaffolding",
    content: `package com.arogyasahaya.local.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object Routes {
    const val HOME = "home"
    const val DASHBOARD = "dashboard"
    const val SETTINGS = "settings"
}

@Composable
fun ArogyaNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToDashboard = {
                    navController.navigate(Routes.DASHBOARD)
                },
                onNavigateToSettings = {
                    navController.navigate(Routes.SETTINGS)
                }
            )
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}`,
  },
  {
    id: "home-screen",
    name: "HomeScreen.kt",
    path: "app/src/main/java/com/arogyasahaya/local/ui/HomeScreen.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "🏠",
    description: "Home screen composable — stub with navigation actions",
    content: `package com.arogyasahaya.local.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToDashboard: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Arogya-Sahaya Local",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your local health companion",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onNavigateToDashboard) {
            Text("Open Dashboard")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onNavigateToSettings) {
            Text("Settings")
        }
    }
}`,
  },
  {
    id: "dashboard-screen",
    name: "DashboardScreen.kt",
    path: "app/src/main/java/com/arogyasahaya/local/ui/DashboardScreen.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "📊",
    description: "Dashboard screen composable — placeholder",
    content: `package com.arogyasahaya.local.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Health Dashboard — charts will render here")
        }
    }
}`,
  },
  {
    id: "settings-screen",
    name: "SettingsScreen.kt",
    path: "app/src/main/java/com/arogyasahaya/local/ui/SettingsScreen.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "⚙️",
    description: "Settings screen composable — placeholder",
    content: `package com.arogyasahaya.local.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Settings — preferences will appear here")
        }
    }
}`,
  },
  {
    id: "theme",
    name: "Theme.kt",
    path: "app/src/main/java/com/arogyasahaya/local/ui/theme/Theme.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "🎨",
    description: "Material3 dynamic theme wrapper",
    content: `package com.arogyasahaya.local.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1B6D3D),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFA4F4BA),
    onPrimaryContainer = Color(0xFF00210E),
    secondary = Color(0xFF4F6353),
    onSecondary = Color.White,
    background = Color(0xFFFCFDF7),
    surface = Color(0xFFFCFDF7),
    onBackground = Color(0xFF1A1C19),
    onSurface = Color(0xFF1A1C19)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF88D7A0),
    onPrimary = Color(0xFF00391B),
    primaryContainer = Color(0xFF00532B),
    onPrimaryContainer = Color(0xFFA4F4BA),
    secondary = Color(0xFFB7CCB9),
    onSecondary = Color(0xFF233427),
    background = Color(0xFF1A1C19),
    surface = Color(0xFF1A1C19),
    onBackground = Color(0xFFE2E3DD),
    onSurface = Color(0xFFE2E3DD)
)

@Composable
fun ArogyaSahayaLocalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}`,
  },
  {
    id: "boot-receiver",
    name: "BootReceiver.kt",
    path: "app/src/main/java/com/arogyasahaya/local/receiver/BootReceiver.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "📡",
    description: "BroadcastReceiver — reschedules WorkManager tasks on device boot",
    content: `package com.arogyasahaya.local.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.arogyasahaya.local.worker.HealthReminderWorker
import java.util.concurrent.TimeUnit

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val reminderRequest = PeriodicWorkRequestBuilder<HealthReminderWorker>(
                repeatInterval = 6,
                repeatIntervalTimeUnit = TimeUnit.HOURS
            ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "health_reminder_work",
                ExistingPeriodicWorkPolicy.KEEP,
                reminderRequest
            )
        }
    }
}`,
  },
  {
    id: "health-reminder-worker",
    name: "HealthReminderWorker.kt",
    path: "app/src/main/java/com/arogyasahaya/local/worker/HealthReminderWorker.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "⏰",
    description: "WorkManager CoroutineWorker — periodic health reminders",
    content: `package com.arogyasahaya.local.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arogyasahaya.local.R

class HealthReminderWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        private const val CHANNEL_ID = "health_reminder_channel"
        private const val NOTIFICATION_ID = 1001
    }

    override suspend fun doWork(): Result {
        createNotificationChannel()
        showNotification()
        return Result.success()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Health Reminders",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Periodic health check reminders"
        }

        val notificationManager =
            appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun showNotification() {
        if (ContextCompat.checkSelfPermission(
                appContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val notification = NotificationCompat.Builder(appContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Health Reminder")
            .setContentText("Time to log your health vitals in Arogya-Sahaya!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(appContext).notify(NOTIFICATION_ID, notification)
    }
}`,
  },
  {
    id: "app-database",
    name: "AppDatabase.kt",
    path: "app/src/main/java/com/arogyasahaya/local/data/local/AppDatabase.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "🗄️",
    description: "Room database — single instance with HealthRecord entity",
    content: `package com.arogyasahaya.local.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arogyasahaya.local.data.local.dao.HealthRecordDao
import com.arogyasahaya.local.data.local.entity.HealthRecord

@Database(
    entities = [HealthRecord::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun healthRecordDao(): HealthRecordDao
}`,
  },
  {
    id: "converters",
    name: "Converters.kt",
    path: "app/src/main/java/com/arogyasahaya/local/data/local/Converters.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "🔄",
    description: "Room type converters — Long ↔ Date mapping",
    content: `package com.arogyasahaya.local.data.local

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}`,
  },
  {
    id: "health-record-entity",
    name: "HealthRecord.kt",
    path: "app/src/main/java/com/arogyasahaya/local/data/local/entity/HealthRecord.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "💾",
    description: "Room @Entity — health vitals data model",
    content: `package com.arogyasahaya.local.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "health_records")
data class HealthRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "systolic_bp")
    val systolicBp: Int,

    @ColumnInfo(name = "diastolic_bp")
    val diastolicBp: Int,

    @ColumnInfo(name = "heart_rate")
    val heartRate: Int,

    @ColumnInfo(name = "blood_sugar")
    val bloodSugar: Float,

    @ColumnInfo(name = "temperature")
    val temperature: Float,

    @ColumnInfo(name = "notes")
    val notes: String = "",

    @ColumnInfo(name = "recorded_at")
    val recordedAt: Date = Date()
)`,
  },
  {
    id: "health-record-dao",
    name: "HealthRecordDao.kt",
    path: "app/src/main/java/com/arogyasahaya/local/data/local/dao/HealthRecordDao.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "🔍",
    description: "Room @Dao — CRUD + Flow queries for health records",
    content: `package com.arogyasahaya.local.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arogyasahaya.local.data.local.entity.HealthRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: HealthRecord): Long

    @Update
    suspend fun update(record: HealthRecord)

    @Delete
    suspend fun delete(record: HealthRecord)

    @Query("SELECT * FROM health_records ORDER BY recorded_at DESC")
    fun getAllRecords(): Flow<List<HealthRecord>>

    @Query("SELECT * FROM health_records WHERE id = :id")
    suspend fun getRecordById(id: Long): HealthRecord?

    @Query("SELECT * FROM health_records WHERE recorded_at BETWEEN :startDate AND :endDate ORDER BY recorded_at ASC")
    fun getRecordsBetween(startDate: Long, endDate: Long): Flow<List<HealthRecord>>

    @Query("SELECT COUNT(*) FROM health_records")
    suspend fun getRecordCount(): Int

    @Query("DELETE FROM health_records")
    suspend fun deleteAllRecords()
}`,
  },
  {
    id: "database-module",
    name: "DatabaseModule.kt",
    path: "app/src/main/java/com/arogyasahaya/local/di/DatabaseModule.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "💉",
    description: "Hilt @Module — provides Room database and DAOs",
    content: `package com.arogyasahaya.local.di

import android.content.Context
import androidx.room.Room
import com.arogyasahaya.local.data.local.AppDatabase
import com.arogyasahaya.local.data.local.dao.HealthRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "arogya_sahaya_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideHealthRecordDao(database: AppDatabase): HealthRecordDao {
        return database.healthRecordDao()
    }
}`,
  },
  {
    id: "datastore-module",
    name: "DataStoreModule.kt",
    path: "app/src/main/java/com/arogyasahaya/local/di/DataStoreModule.kt",
    language: "kotlin",
    category: "kotlin",
    icon: "💉",
    description: "Hilt @Module — provides DataStore<Preferences>",
    content: `package com.arogyasahaya.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "arogya_preferences"
)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.dataStore
    }
}`,
  },
  {
    id: "proguard-rules",
    name: "proguard-rules.pro",
    path: "app/proguard-rules.pro",
    language: "properties",
    category: "gradle",
    icon: "🛡️",
    description: "ProGuard rules — keeps for Room, Hilt, and MPAndroidChart",
    content: `# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager { *; }

# MPAndroidChart
-keep class com.github.mikephil.charting.** { *; }
-dontwarn io.realm.**

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Keep data classes used by Room
-keepclassmembers class com.arogyasahaya.local.data.local.entity.** {
    <fields>;
    <init>(...);
}`,
  },
  {
    id: "gradle-properties",
    name: "gradle.properties",
    path: "gradle.properties",
    language: "properties",
    category: "gradle",
    icon: "📝",
    description: "Gradle properties — JVM args, AndroidX, non-transitive R classes",
    content: `org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
kotlin.code.style=official
android.nonTransitiveRClass=true`,
  },
];

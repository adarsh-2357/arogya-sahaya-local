package com.arogyasahaya.local.util

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.arogyasahaya.local.R
import com.arogyasahaya.local.receiver.MedicineNotificationActionReceiver
import com.arogyasahaya.local.ui.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val CHANNEL_ID = "MEDICINE_REMINDERS"
        const val CHANNEL_NAME = "Medicine Reminders"
        const val NOTIFICATION_ID_BASE = 10000
    }

    init {
        createNotificationChannel()
    }

    fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Reminders to take your scheduled medicines on time"
            enableVibration(true)
            enableLights(true)
            lightColor = android.graphics.Color.parseColor("#4CAF50")
            vibrationPattern = longArrayOf(0, 250, 250, 250)
            lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            setShowBadge(true)
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    fun showMedicineNotification(
        medicineName: String,
        dosage: String,
        time: String
    ) {
        if (!hasNotificationPermission()) {
            return
        }

        val notificationId = generateNotificationId(medicineName, time)

        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val timeEmoji = when (time.lowercase()) {
            "morning" -> "🌅"
            "afternoon" -> "☀️"
            "night" -> "🌙"
            else -> "💊"
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Time for your medicine $timeEmoji")
            .setContentText("$medicineName — $dosage")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("It's $time time!\n\nPlease take your $medicineName ($dosage) as prescribed by your doctor.\n\nStay consistent with your medication schedule for better health outcomes.")
                    .setSummaryText("$medicineName • $dosage")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(false)
            .setOngoing(true)
            .setOnlyAlertOnce(false)
            .setContentIntent(pendingIntent)
            .setFullScreenIntent(pendingIntent, true)
            .addAction(
                R.drawable.ic_launcher_foreground,
                "I Took It",
                createDismissPendingIntent(notificationId)
            )
            .addAction(
                R.drawable.ic_launcher_foreground,
                "Snooze 15min",
                createSnoozePendingIntent(medicineName, dosage, time, notificationId)
            )
            .build()

        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }

    fun showMedicationTakenConfirmation(medicineName: String) {
        if (!hasNotificationPermission()) {
            return
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Great job! ✓")
            .setContentText("You've taken your $medicineName")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Well done! You've successfully taken your $medicineName. Keep up the good work with your medication schedule!")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .build()

        NotificationManagerCompat.from(context).notify(
            System.currentTimeMillis().toInt() % 10000,
            notification
        )
    }

    fun cancelNotification(medicineName: String, time: String) {
        val notificationId = generateNotificationId(medicineName, time)
        NotificationManagerCompat.from(context).cancel(notificationId)
    }

    fun cancelAllNotifications() {
        NotificationManagerCompat.from(context).cancelAll()
    }

    private fun generateNotificationId(medicineName: String, time: String): Int {
        val baseHash = medicineName.hashCode() % 10000
        val timeOffset = when (time.lowercase()) {
            "morning" -> 0
            "afternoon" -> 1000
            "night" -> 2000
            else -> 3000
        }
        return NOTIFICATION_ID_BASE + (baseHash + timeOffset) % 1000
    }

    private fun createDismissPendingIntent(notificationId: Int): PendingIntent {
        val intent = Intent(context, MedicineNotificationActionReceiver::class.java).apply {
            putExtra("action", "DISMISS")
            putExtra("notification_id", notificationId)
        }
        return PendingIntent.getBroadcast(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createSnoozePendingIntent(
        medicineName: String,
        dosage: String,
        time: String,
        notificationId: Int
    ): PendingIntent {
        val intent = Intent(context, MedicineNotificationActionReceiver::class.java).apply {
            putExtra("action", "SNOOZE")
            putExtra("notification_id", notificationId)
            putExtra("medicine_name", medicineName)
            putExtra("dosage", dosage)
            putExtra("time", time)
        }
        return PendingIntent.getBroadcast(
            context,
            notificationId + 5000,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}

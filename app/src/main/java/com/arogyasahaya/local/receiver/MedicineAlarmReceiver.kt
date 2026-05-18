package com.arogyasahaya.local.receiver

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.arogyasahaya.local.R
import com.arogyasahaya.local.util.AlarmScheduler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MedicineAlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    companion object {
        private const val CHANNEL_ID = "medicine_reminder_channel"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val medicineId = intent.getLongExtra(AlarmScheduler.EXTRA_MEDICINE_ID, -1L)
        val medicineName = intent.getStringExtra(AlarmScheduler.EXTRA_MEDICINE_NAME) ?: "Medicine"
        val dosage = intent.getStringExtra(AlarmScheduler.EXTRA_DOSAGE) ?: ""
        val timeLabel = intent.getStringExtra(AlarmScheduler.EXTRA_TIME_LABEL) ?: ""

        if (medicineId == -1L) return

        // Reschedule for next day
        alarmScheduler.scheduleMedicineAlarms(
            medicineId,
            medicineName,
            dosage,
            listOf(timeLabel)
        )

        createNotificationChannel(context)

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val timeEmoji = when (timeLabel.lowercase()) {
            "morning" -> "🌅"
            "afternoon" -> "☀️"
            "night" -> "🌙"
            else -> "💊"
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("$timeEmoji Medicine Reminder")
            .setContentText("Time to take $medicineName — $dosage")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("It's $timeLabel time! Please take your $medicineName ($dosage).")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val notificationId = (medicineId * 10 + System.currentTimeMillis() % 10).toInt()
        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }

    private fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Medicine Reminders",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Reminders to take your scheduled medicines"
            enableVibration(true)
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

package com.arogyasahaya.local.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.arogyasahaya.local.util.AlarmScheduler
import com.arogyasahaya.local.util.NotificationHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MedicineNotificationActionReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationHelper: NotificationHelper

    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.getStringExtra("action")
        val notificationId = intent.getIntExtra("notification_id", -1)

        when (action) {
            "DISMISS" -> {
                if (notificationId != -1) {
                    NotificationManagerCompat.from(context).cancel(notificationId)
                }
            }

            "SNOOZE" -> {
                val medicineName = intent.getStringExtra("medicine_name") ?: "Medicine"
                val dosage = intent.getStringExtra("dosage") ?: ""
                val time = intent.getStringExtra("time") ?: "now"

                if (notificationId != -1) {
                    NotificationManagerCompat.from(context).cancel(notificationId)
                }

                // Snooze for 15 minutes (900,000 milliseconds)
                alarmScheduler.scheduleOneTimeReminder(
                    medicineId = (notificationId % 1000).toLong(),
                    medicineName = medicineName,
                    dosage = dosage,
                    timeLabel = "$time (Snoozed)",
                    delayMillis = 15 * 60 * 1000L
                )
            }
        }
    }
}

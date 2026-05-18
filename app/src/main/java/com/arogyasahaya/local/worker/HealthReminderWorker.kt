package com.arogyasahaya.local.worker

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
}

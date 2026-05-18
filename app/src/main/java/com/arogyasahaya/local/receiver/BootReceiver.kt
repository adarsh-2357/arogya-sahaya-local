package com.arogyasahaya.local.receiver

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
}

package com.arogyasahaya.local.worker

import android.app.ForegroundServiceStartNotAllowedException
import android.content.Context
import android.os.Build
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.core.app.NotificationCompat
import com.arogyasahaya.local.R
import com.arogyasahaya.local.util.AlarmScheduler
import com.arogyasahaya.local.util.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class MedicineReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val notificationHelper: NotificationHelper
) : CoroutineWorker(context, workerParams) {

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "medicine_work_channel"
        private const val NOTIFICATION_ID = 20000
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val medicineId = inputData.getLong(AlarmScheduler.EXTRA_MEDICINE_ID, -1L)
        val medicineName = inputData.getString(AlarmScheduler.EXTRA_MEDICINE_NAME) ?: "Medicine"
        val dosage = inputData.getString(AlarmScheduler.EXTRA_DOSAGE) ?: ""
        val timeLabel = inputData.getString(AlarmScheduler.EXTRA_TIME_LABEL) ?: "now"

        if (medicineId == -1L) {
            return@withContext Result.failure()
        }

        try {
            notificationHelper.showMedicineNotification(
                medicineName = medicineName,
                dosage = dosage,
                time = timeLabel
            )

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        val medicineName = inputData.getString(AlarmScheduler.EXTRA_MEDICINE_NAME) ?: "Medicine"
        val dosage = inputData.getString(AlarmScheduler.EXTRA_DOSAGE) ?: ""
        val timeLabel = inputData.getString(AlarmScheduler.EXTRA_TIME_LABEL) ?: "now"

        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Medicine Reminder")
            .setContentText("Preparing reminder for $medicineName...")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setOngoing(true)
            .setSilent(true)
            .build()

        return ForegroundInfo(NOTIFICATION_ID, notification)
    }
}

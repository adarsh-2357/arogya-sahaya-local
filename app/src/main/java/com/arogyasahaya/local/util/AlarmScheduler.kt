package com.arogyasahaya.local.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.arogyasahaya.local.receiver.MedicineAlarmReceiver
import com.arogyasahaya.local.worker.MedicineReminderWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmScheduler @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val MORNING_HOUR = 8
        private const val MORNING_MINUTE = 0

        private const val AFTERNOON_HOUR = 13
        private const val AFTERNOON_MINUTE = 0

        private const val NIGHT_HOUR = 21
        private const val NIGHT_MINUTE = 0

        const val EXTRA_MEDICINE_ID = "medicine_id"
        const val EXTRA_MEDICINE_NAME = "medicine_name"
        const val EXTRA_DOSAGE = "dosage"
        const val EXTRA_TIME_LABEL = "time_label"
    }

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val workManager = WorkManager.getInstance(context)

    fun scheduleMedicineAlarms(
        medicineId: Long,
        medicineName: String,
        dosage: String,
        timesOfDay: List<String>
    ) {
        timesOfDay.forEach { timeLabel ->
            val intent = Intent(context, MedicineAlarmReceiver::class.java).apply {
                putExtra(EXTRA_MEDICINE_ID, medicineId)
                putExtra(EXTRA_MEDICINE_NAME, medicineName)
                putExtra(EXTRA_DOSAGE, dosage)
                putExtra(EXTRA_TIME_LABEL, timeLabel)
            }

            val requestCode = generateRequestCode(medicineId, timeLabel)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val triggerTime = calculateInitialDelayForTime(timeLabel) + System.currentTimeMillis()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        triggerTime,
                        pendingIntent
                    )
                } else {
                    alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        triggerTime,
                        pendingIntent
                    )
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
        }
    }

    fun cancelAllAlarmsForMedicine(medicineId: Long) {
        val times = listOf("Morning", "Afternoon", "Night")
        times.forEach { timeLabel ->
            val intent = Intent(context, MedicineAlarmReceiver::class.java)
            val requestCode = generateRequestCode(medicineId, timeLabel)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.cancel(pendingIntent)
        }
        
        // Also cancel any WorkManager tasks associated with this medicine
        workManager.cancelAllWorkByTag("medicine_$medicineId")
    }

    fun scheduleOneTimeReminder(
        medicineId: Long,
        medicineName: String,
        dosage: String,
        timeLabel: String,
        delayMillis: Long
    ) {
        val workTag = "onetime_medicine_${medicineId}_${System.currentTimeMillis()}"

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<MedicineReminderWorker>()
            .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .setExpedited(
                androidx.work.OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
            )
            .addTag(workTag)
            .addTag("medicine_$medicineId")
            .setInputData(
                androidx.work.Data.Builder()
                    .putLong(EXTRA_MEDICINE_ID, medicineId)
                    .putString(EXTRA_MEDICINE_NAME, medicineName)
                    .putString(EXTRA_DOSAGE, dosage)
                    .putString(EXTRA_TIME_LABEL, timeLabel)
                    .build()
            )
            .build()

        workManager.enqueue(workRequest)
    }

    private fun generateRequestCode(medicineId: Long, timeLabel: String): Int {
        val timeOffset = when (timeLabel.lowercase()) {
            "morning" -> 1
            "afternoon" -> 2
            "night" -> 3
            else -> 0
        }
        return (medicineId * 10 + timeOffset).toInt()
    }

    private fun calculateInitialDelayForTime(timeLabel: String): Long {
        val (hour, minute) = when (timeLabel.lowercase()) {
            "morning" -> MORNING_HOUR to MORNING_MINUTE
            "afternoon" -> AFTERNOON_HOUR to AFTERNOON_MINUTE
            "night" -> NIGHT_HOUR to NIGHT_MINUTE
            else -> MORNING_HOUR to MORNING_MINUTE
        }

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }

        val targetTimeMillis = calendar.timeInMillis
        val currentTimeMillis = System.currentTimeMillis()

        return (targetTimeMillis - currentTimeMillis).coerceAtLeast(0)
    }
}

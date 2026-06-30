package com.example.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.data.localization.LocKey
import com.example.data.localization.Localization

class NotificationAndDndManager(private val context: Context) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID_ALERTS,
                "FocusLedger Focus Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for session completions and breaks"
                enableVibration(true)
            }
            val reminderChannel = NotificationChannel(
                CHANNEL_ID_REMINDERS,
                "FocusLedger Daily Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Daily productivity encouragement"
            }
            notificationManager.createNotificationChannel(channel)
            notificationManager.createNotificationChannel(reminderChannel)
        }
    }

    fun hasDndPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager.isNotificationPolicyAccessGranted
        } else {
            true
        }
    }

    fun requestDndPermissionIntent(): Intent? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasDndPermission()) {
            Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
        } else {
            null
        }
    }

    fun setDndMode(enabled: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                if (notificationManager.isNotificationPolicyAccessGranted) {
                    val filter = if (enabled) {
                        NotificationManager.INTERRUPTION_FILTER_NONE
                    } else {
                        NotificationManager.INTERRUPTION_FILTER_ALL
                    }
                    notificationManager.setInterruptionFilter(filter)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showSuccessNotification(language: String) {
        val title = Localization.get(LocKey.SUCCESS_NOTIF, language)
        val body = Localization.get(LocKey.SUCCESS_NOTIF_BODY, language)
        showNotification(CHANNEL_ID_ALERTS, NOTIF_ID_FOCUS, title, body)
    }

    fun showFailureNotification(language: String) {
        val title = Localization.get(LocKey.FAILURE_LOSS_NOTIF, language)
        val body = Localization.get(LocKey.FAILURE_NOTIF_BODY, language)
        showNotification(CHANNEL_ID_ALERTS, NOTIF_ID_FOCUS, title, body)
    }

    fun showBreakEndNotification(language: String) {
        val title = Localization.get(LocKey.BREAK_END_NOTIF_TITLE, language)
        val body = Localization.get(LocKey.BREAK_END_NOTIF_BODY, language)
        showNotification(CHANNEL_ID_ALERTS, NOTIF_ID_BREAK_END, title, body)
    }

    private fun showNotification(
        channelId: String,
        notificationId: Int,
        title: String,
        body: String
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    companion object {
        const val CHANNEL_ID_ALERTS = "focus_alerts"
        const val CHANNEL_ID_REMINDERS = "daily_reminders"
        
        const val NOTIF_ID_FOCUS = 1001
        const val NOTIF_ID_BREAK_END = 1002
        const val NOTIF_ID_DAILY_REMINDER = 1003
    }
}

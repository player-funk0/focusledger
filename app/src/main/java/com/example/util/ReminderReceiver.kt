package com.example.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.data.preferences.PreferencesManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val prefManager = PreferencesManager(context)
        
        val language = runBlocking {
            try {
                prefManager.languageFlow.first()
            } catch (e: Exception) {
                "English"
            }
        }

        val title = if (language.lowercase().contains("arabic")) {
            "حان وقت التركيز اليومي!"
        } else if (language.lowercase().contains("slang")) {
            "يلا يا وحش ركز النهاردة!"
        } else if (language.lowercase().contains("franco")) {
            "Yalla rkez elnharda!"
        } else {
            "Daily Focus Reminder!"
        }

        val body = if (language.lowercase().contains("arabic")) {
            "لا تكسر سلسلتك اليومية وقم بتطوير بطاقة الإنتاجية الخاصة بك الآن."
        } else if (language.lowercase().contains("slang")) {
            "سيبك من السوشيال ميديا وتعال طور كارت الإنتاجية بتاعك في ثواني."
        } else if (language.lowercase().contains("franco")) {
            "Sybak mn el social media w t3ala tawar krtk dlw2ty."
        } else {
            "Don't break your daily streak. Upgrade your Productivity ID card now."
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationAndDndManager.CHANNEL_ID_REMINDERS,
                "FocusLedger Daily Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val mainIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            NotificationAndDndManager.NOTIF_ID_DAILY_REMINDER,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, NotificationAndDndManager.CHANNEL_ID_REMINDERS)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NotificationAndDndManager.NOTIF_ID_DAILY_REMINDER, notification)
    }
}

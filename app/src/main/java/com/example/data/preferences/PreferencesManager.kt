package com.example.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "focus_ledger_preferences")

class PreferencesManager(private val context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val KEY_LANGUAGE = stringPreferencesKey("language")
        val KEY_POMODORO_MODE = booleanPreferencesKey("pomodoro_mode")
        val KEY_AUTO_DND = booleanPreferencesKey("auto_dnd")
        val KEY_DAILY_REMINDER_ENABLED = booleanPreferencesKey("daily_reminder_enabled")
        val KEY_DAILY_REMINDER_TIME = stringPreferencesKey("daily_reminder_time")
        val KEY_ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val KEY_POMODORO_WORK_MINUTES = intPreferencesKey("pomodoro_work_minutes")
        val KEY_POMODORO_BREAK_MINUTES = intPreferencesKey("pomodoro_break_minutes")
    }

    val languageFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[KEY_LANGUAGE] ?: "English"
    }

    val pomodoroModeFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[KEY_POMODORO_MODE] ?: true
    }

    val autoDndFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[KEY_AUTO_DND] ?: false
    }

    val dailyReminderEnabledFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[KEY_DAILY_REMINDER_ENABLED] ?: false
    }

    val dailyReminderTimeFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[KEY_DAILY_REMINDER_TIME] ?: "08:30"
    }

    val onboardingCompletedFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[KEY_ONBOARDING_COMPLETED] ?: false
    }

    val pomodoroWorkMinutesFlow: Flow<Int> = dataStore.data.map { preferences ->
        preferences[KEY_POMODORO_WORK_MINUTES] ?: 25
    }

    val pomodoroBreakMinutesFlow: Flow<Int> = dataStore.data.map { preferences ->
        preferences[KEY_POMODORO_BREAK_MINUTES] ?: 5
    }

    suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[KEY_LANGUAGE] = language
        }
    }

    suspend fun setPomodoroMode(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY_POMODORO_MODE] = enabled
        }
    }

    suspend fun setAutoDnd(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTO_DND] = enabled
        }
    }

    suspend fun setDailyReminderEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY_DAILY_REMINDER_ENABLED] = enabled
        }
    }

    suspend fun setDailyReminderTime(time: String) {
        dataStore.edit { preferences ->
            preferences[KEY_DAILY_REMINDER_TIME] = time
        }
    }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY_ONBOARDING_COMPLETED] = completed
        }
    }

    suspend fun setPomodoroWorkMinutes(minutes: Int) {
        dataStore.edit { preferences ->
            preferences[KEY_POMODORO_WORK_MINUTES] = minutes
        }
    }

    suspend fun setPomodoroBreakMinutes(minutes: Int) {
        dataStore.edit { preferences ->
            preferences[KEY_POMODORO_BREAK_MINUTES] = minutes
        }
    }
}

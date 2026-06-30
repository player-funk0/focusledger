package com.example.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStatsEntity(
    @PrimaryKey val id: Int = 1,
    val username: String = "Focus Member",
    val bio: String = "Focus Mode On",
    val avatarNumber: Int = 0,
    val tokenBalance: Int = 30, // Starts with 30 starter Energy Tokens welcome gift
    val xp: Int = 0, // Total focus minutes starts at zero
    val unlockedGradients: String = "Brushed Titanium", // Comma-separated
    val unlockedFonts: String = "Inter Sans", // Comma-separated
    val unlockedTitles: String = "Casual Focus", // Comma-separated
    val unlockedSounds: String = "Zenith, Cosmic Drift, Subtle Bell, Digital Beep", // Comma-separated
    val activeGradient: String = "Brushed Titanium",
    val activeFont: String = "Inter Sans",
    val activeTitle: String = "Casual Focus",
    val activeSound: String = "Zenith",
    val customMusicUri: String? = null,
    val useCustomMusic: Boolean = false
)

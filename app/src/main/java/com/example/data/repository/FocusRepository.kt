package com.example.data.repository

import com.example.data.database.FocusSessionDao
import com.example.data.database.FocusSessionEntity
import com.example.data.database.UserStatsDao
import com.example.data.database.UserStatsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart

class FocusRepository(
    private val userStatsDao: UserStatsDao,
    private val focusSessionDao: FocusSessionDao
) {
    val userStats: Flow<UserStatsEntity?> = userStatsDao.getUserStats()
        .onStart {
            // Check if userStats is empty, if so, insert default
            val current = userStatsDao.getUserStats().firstOrNull()
            if (current == null) {
                userStatsDao.insertUserStats(UserStatsEntity())
            }
        }

    val allSessions: Flow<List<FocusSessionEntity>> = focusSessionDao.getAllSessions()

    suspend fun updateUserStats(stats: UserStatsEntity) {
        userStatsDao.insertUserStats(stats)
    }

    suspend fun insertSession(session: FocusSessionEntity) {
        focusSessionDao.insertSession(session)
    }

    suspend fun clearSessions() {
        focusSessionDao.clearAll()
    }
}

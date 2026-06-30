package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.database.FocusSessionEntity
import com.example.data.database.UserStatsEntity
import com.example.data.localization.LocKey
import com.example.data.localization.Localization
import com.example.ui.theme.CustomizationStyles
import com.example.ui.viewmodel.FocusViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LedgerScreen(
    viewModel: FocusViewModel
) {
    val language by viewModel.language.collectAsState()
    val userStats by viewModel.userStats.collectAsState()
    val sessions by viewModel.allSessions.collectAsState()

    val stats = userStats ?: UserStatsEntity()
    val activeFontFamily = CustomizationStyles.getFontFamily(stats.activeFont)

    // Calculate dynamic metrics
    val totalSuccessMin = sessions.filter { it.isSuccessful }.sumOf { it.durationMinutes }
    val brokenCount = sessions.filter { !it.isSuccessful }.size
    val totalCount = sessions.size
    val efficiencyRatio = if (totalCount > 0) {
        (sessions.filter { it.isSuccessful }.size.toFloat() / totalCount.toFloat()) * 100f
    } else {
        0f
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "FOCUSLEDGER",
                        style = MaterialTheme.typography.labelSmall,
                        letterSpacing = 2.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Screen Header
            Column {
                Text(
                    text = Localization.get(LocKey.THE_LEDGER, language),
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = activeFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = Localization.get(LocKey.LEDGER_SUBTITLE, language),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            // SECTION 1: Metrics Overview (Bento Grid)
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Metric 1: Total Focused Minutes (Hero, full width)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CustomizationStyles.LiquidGlassContainerColor),
                    border = CustomizationStyles.LiquidGlassBorder
                ) {
                    Box(modifier = CustomizationStyles.LiquidGlassModifier) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                        Text(
                            text = Localization.get(LocKey.TOTAL_FOCUSED_MIN, language),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = String.format("%,d", totalSuccessMin),
                            style = MaterialTheme.typography.headlineLarge,
                            fontFamily = activeFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "0% " + Localization.get(LocKey.VS_LAST_WEEK, language),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        }
                    }
                }

                // Bento Row: Broken Sessions & Efficiency Ratio side-by-side
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Metric 2: Broken Sessions (Left)
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CustomizationStyles.LiquidGlassContainerColor),
                        border = CustomizationStyles.LiquidGlassBorder
                    ) {
                        Box(modifier = CustomizationStyles.LiquidGlassModifier) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                            Text(
                                text = Localization.get(LocKey.BROKEN_SESSIONS, language),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Gray,
                                letterSpacing = 1.sp,
                                maxLines = 1
                            )
                            Text(
                                text = "$brokenCount",
                                style = MaterialTheme.typography.headlineLarge,
                                fontFamily = activeFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "0% " + Localization.get(LocKey.IMPROVEMENT, language),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White.copy(alpha = 0.6f)
                                )
                            }
                        }
                    }

                    // Metric 3: Efficiency Ratio (Right)
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CustomizationStyles.LiquidGlassContainerColor),
                        border = CustomizationStyles.LiquidGlassBorder
                    ) {
                        Box(modifier = CustomizationStyles.LiquidGlassModifier) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                            Text(
                                text = Localization.get(LocKey.EFFICIENCY_RATIO, language),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Gray,
                                letterSpacing = 1.sp,
                                maxLines = 1
                            )
                            Text(
                                text = String.format("%.1f%%", efficiencyRatio),
                                style = MaterialTheme.typography.headlineLarge,
                                fontFamily = activeFontFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text("✓", color = Color.White.copy(alpha = 0.8f), fontWeight = FontWeight.Bold)
                                Text(
                                    text = Localization.get(LocKey.PEAK_PERF, language),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }

            // SECTION 2: Weekly Output Distribution (Bar Chart)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CustomizationStyles.LiquidGlassContainerColor),
                border = CustomizationStyles.LiquidGlassBorder
            ) {
                Box(modifier = CustomizationStyles.LiquidGlassModifier) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = Localization.get(LocKey.WEEKLY_OUTPUT, language),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.Black)
                                .padding(2.dp)
                                .widthIn(min = 100.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(0xFF2C2C2C))
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = Localization.get(LocKey.WEEK, language),
                                    fontSize = 10.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = Localization.get(LocKey.MONTH, language),
                                    fontSize = 10.sp,
                                    color = Color.Gray,
                                    maxLines = 1
                                )
                            }
                        }
                    }

                    // Pure 2D Geometric Bar Chart using Row/Column
                    val weekdays = listOf(
                        Localization.get(LocKey.MON, language),
                        Localization.get(LocKey.TUE, language),
                        Localization.get(LocKey.WED, language),
                        Localization.get(LocKey.THU, language),
                        Localization.get(LocKey.FRI, language),
                        Localization.get(LocKey.SAT, language),
                        Localization.get(LocKey.SUN, language)
                    )
                    
                    // Calculate real heights based on last 7 days of successful sessions
                    val calendar = Calendar.getInstance()
                    val heights = remember(sessions) {
                        val dailyMinutes = MutableList(7) { 0 }
                        val now = System.currentTimeMillis()
                        val oneDayMs = 24 * 60 * 60 * 1000L
                        
                        sessions.filter { it.isSuccessful }.forEach { session ->
                            val diffDays = ((now - session.timestamp) / oneDayMs).toInt()
                            if (diffDays in 0..6) {
                                // Get day of week (0=Mon, 6=Sun)
                                val sessionCal = Calendar.getInstance().apply { timeInMillis = session.timestamp }
                                val dayOfWeek = when (sessionCal.get(Calendar.DAY_OF_WEEK)) {
                                    Calendar.MONDAY -> 0
                                    Calendar.TUESDAY -> 1
                                    Calendar.WEDNESDAY -> 2
                                    Calendar.THURSDAY -> 3
                                    Calendar.FRIDAY -> 4
                                    Calendar.SATURDAY -> 5
                                    Calendar.SUNDAY -> 6
                                    else -> 0
                                }
                                dailyMinutes[dayOfWeek] += session.durationMinutes
                            }
                        }
                        
                        val maxMin = dailyMinutes.maxOrNull()?.coerceAtLeast(60) ?: 60
                        dailyMinutes.map { it.toFloat() / maxMin.toFloat() }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        val currentDayOfWeek = when (calendar.get(Calendar.DAY_OF_WEEK)) {
                            Calendar.MONDAY -> 0
                            Calendar.TUESDAY -> 1
                            Calendar.WEDNESDAY -> 2
                            Calendar.THURSDAY -> 3
                            Calendar.FRIDAY -> 4
                            Calendar.SATURDAY -> 5
                            Calendar.SUNDAY -> 6
                            else -> 0
                        }
                        
                        weekdays.forEachIndexed { index, day ->
                            val heightPercent = heights[index]
                            val isMax = index == currentDayOfWeek // Highlight today

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(heightPercent)
                                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                        .background(if (isMax) Color.White else Color(0xFF2C2C2C))
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = day,
                                    fontSize = 10.sp,
                                    color = if (isMax) Color.White else Color.Gray,
                                    fontWeight = if (isMax) FontWeight.Bold else FontWeight.Normal,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            // SECTION 3: Recent Audits (Session History)
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = Localization.get(LocKey.RECENT_AUDITS, language),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = Localization.get(LocKey.SHOW_ALL, language),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CustomizationStyles.LiquidGlassContainerColor),
                    border = CustomizationStyles.LiquidGlassBorder
                ) {
                    Box(modifier = CustomizationStyles.LiquidGlassModifier) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                        if (sessions.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(Localization.get(LocKey.NO_SESSIONS, language), color = Color.Gray)
                            }
                        } else {
                            sessions.take(10).forEachIndexed { index, session ->
                                if (index > 0) {
                                    Divider(color = Color(0xFF1C1C1C))
                                }
                                SessionHistoryItem(session, language)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
}

@Composable
fun SessionHistoryItem(session: FocusSessionEntity, language: String) {
    val sdf = remember { SimpleDateFormat("hh:mm a", Locale.ENGLISH) }
    val formattedTime = sdf.format(Date(session.timestamp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon showing success vs interrupted
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (session.isSuccessful) Color(0xFF1C1C1C) else Color(0xFF2C2C2C)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (session.isSuccessful) "✓" else "✕",
                    color = if (session.isSuccessful) Color.White else Color(0xFFFFB4AB),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Column {
                Text(
                    text = session.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
                Text(
                    text = "${Localization.get(LocKey.TODAY_PREFIX, language)} • $formattedTime",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${session.durationMinutes} ${Localization.get(LocKey.MINUTES, language)}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = if (session.isSuccessful) Color.White else Color(0xFFFFB4AB)
            )
            Text(
                text = if (session.isSuccessful) {
                    Localization.get(LocKey.SUCCESS, language)
                } else {
                    Localization.get(LocKey.INTERRUPTED, language)
                },
                style = MaterialTheme.typography.labelSmall,
                fontSize = 9.sp,
                color = if (session.isSuccessful) Color.White.copy(alpha = 0.6f) else Color(0xFFFFB4AB)
            )
        }
    }
}

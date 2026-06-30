package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.database.UserStatsEntity
import com.example.data.localization.LocKey
import com.example.data.localization.Localization
import com.example.ui.theme.CustomizationStyles
import com.example.ui.viewmodel.FocusViewModel
import com.example.ui.viewmodel.TimerState

@Composable
fun FocusScreen(
    viewModel: FocusViewModel
) {
    val timeLeftSeconds by viewModel.timeLeftSeconds.collectAsState()
    val timerState by viewModel.timerState.collectAsState()
    val language by viewModel.language.collectAsState()
    val userStats by viewModel.userStats.collectAsState()

    val stats = userStats ?: UserStatsEntity()
    val activeFontFamily = CustomizationStyles.getFontFamily(stats.activeFont)

    // Format HH:MM:SS or MM:SS
    val hours = timeLeftSeconds / 3600
    val minutes = (timeLeftSeconds % 3600) / 60
    val seconds = timeLeftSeconds % 60
    val formattedTime = if (hours > 0) {
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } else {
        String.format("%02d:%02d", minutes, seconds)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .testTag("focus_screen_canvas")
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Glassmorphic Refractive Frame for the digital clock
            Box(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.07f),
                                Color.White.copy(alpha = 0.02f)
                            )
                        )
                    )
                    .border(
                        BorderStroke(
                            1.dp,
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.20f),
                                    Color.White.copy(alpha = 0.02f)
                                )
                            )
                        ),
                        RoundedCornerShape(32.dp)
                    )
                    .padding(horizontal = 48.dp, vertical = 36.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Oversized ultra-thin digital clock
                    Text(
                        text = formattedTime,
                        fontSize = if (hours > 0) 64.sp else 84.sp,
                        fontFamily = activeFontFamily,
                        fontWeight = FontWeight.ExtraLight,
                        color = Color.White,
                        letterSpacing = 0.sp, // Removed negative letter spacing to prevent overlapping
                        softWrap = false,
                        modifier = Modifier.testTag("countdown_clock")
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Calm status text
                    Text(
                        text = if (timerState == TimerState.BREAK) {
                            Localization.get(LocKey.BREAK_TIME_MSG, language).uppercase()
                        } else {
                            Localization.get(LocKey.FOCUS_SCREEN_MSG, language).uppercase()
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.6f),
                        letterSpacing = 2.sp
                    )
                }
            }
        }

        // Quiet action: Quit session at the bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp)
        ) {
            Text(
                text = Localization.get(LocKey.QUIT_SESSION, language),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .clickable { viewModel.quitSession() }
                    .padding(8.dp)
                    .testTag("quit_session_action")
            )
        }
    }
}


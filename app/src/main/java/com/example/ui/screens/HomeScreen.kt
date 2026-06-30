package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import android.view.View
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.database.UserStatsEntity
import com.example.data.localization.LocKey
import com.example.data.localization.Localization
import com.example.ui.theme.CustomizationStyles
import com.example.ui.viewmodel.FocusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: FocusViewModel,
    onNavigateToSettings: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val context = LocalContext.current
    val userStats by viewModel.userStats.collectAsState()
    val language by viewModel.language.collectAsState()
    val pomodoroMode by viewModel.pomodoroMode.collectAsState()
    val freeFlowMinutes by viewModel.selectedFreeFlowMinutes.collectAsState()
    val pomodoroWorkMinutes by viewModel.pomodoroWorkMinutes.collectAsState()
    val pomodoroBreakMinutes by viewModel.pomodoroBreakMinutes.collectAsState()

    val stats = userStats ?: UserStatsEntity()
    val view = LocalView.current

    val activeGradientBrush = CustomizationStyles.getGradientBrush(stats.activeGradient)
    val activeFontFamily = CustomizationStyles.getFontFamily(stats.activeFont)
    val activeFontWeight = CustomizationStyles.getFontWeight(stats.activeFont)

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
                actions = {
                    IconButton(
                        onClick = onNavigateToSettings,
                        modifier = Modifier.testTag("settings_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = Localization.get(LocKey.SETTINGS, language),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // SECTION 1: Dynamic Personal Productivity ID Card with Apple Liquid Glass Effect
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.586f) // Standard business card ratio
                    .testTag("productivity_card")
                    .clickable { onNavigateToProfile() },
                shape = RoundedCornerShape(20.dp),
                // Glowing refractive glass-morphic border
                border = BorderStroke(
                    width = 1.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.25f),
                            Color.White.copy(alpha = 0.05f)
                        )
                    )
                ),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(activeGradientBrush)
                ) {
                    // --- The Frosted Glass Pane Layer ---
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.08f),
                                        Color.White.copy(alpha = 0.02f)
                                    )
                                )
                            )
                            .padding(24.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Top row: Member identifier & Level
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = Localization.get(LocKey.MEMBER_IDENTITY, language),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 10.sp,
                                        color = Color.White.copy(alpha = 0.6f),
                                        letterSpacing = 1.sp
                                    )
                                    Text(
                                        text = stats.username,
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontFamily = activeFontFamily,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.White
                                    )
                                }
                            }

                            // Bottom row: Stats & Dynamic Title
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Column {
                                    Text(
                                        text = Localization.get(LocKey.TOTAL_FOCUS, language).uppercase(),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 9.sp,
                                        color = Color.White.copy(alpha = 0.6f)
                                    )
                                    Text(
                                        text = "${stats.xp} ${Localization.get(LocKey.MINUTES, language)}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontFamily = activeFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }

                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = Localization.get(LocKey.TITLE, language),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 9.sp,
                                        color = Color.White.copy(alpha = 0.6f)
                                    )
                                    Text(
                                        text = Localization.getDynamicTitle(stats.xp, language, stats.activeTitle),
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontFamily = activeFontFamily,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // SECTION 1.5: Quick Music Access
            if (stats.useCustomMusic && stats.customMusicUri != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CustomizationStyles.LiquidGlassContainerColor),
                    border = CustomizationStyles.LiquidGlassBorder
                ) {
                    Box(modifier = CustomizationStyles.LiquidGlassModifier) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.05f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🎵", fontSize = 18.sp)
                        }
                        
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = Localization.get(LocKey.CUSTOM_MUSIC, language).uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.4f),
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = stats.customMusicUri?.substringAfterLast("/") ?: Localization.get(LocKey.DEFAULT, language),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1
                            )
                        }
                        
                        TextButton(onClick = onNavigateToSettings) {
                            Text(
                                text = Localization.get(LocKey.EQUIP_ACTION, language),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White
                            )
                        }
                    }
                }
            }
            }

            // SECTION 2: Timer Controller Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CustomizationStyles.LiquidGlassContainerColor),
                border = CustomizationStyles.LiquidGlassBorder
            ) {
                Box(modifier = CustomizationStyles.LiquidGlassModifier) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Pomodoro ON/OFF switch row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = Localization.get(LocKey.POMODORO_SWITCH, language),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )
                            Text(
                                text = if (pomodoroMode) {
                                    Localization.get(LocKey.POMODORO_ON, language)
                                } else {
                                    Localization.get(LocKey.POMODORO_OFF, language)
                                },
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Gray
                            )
                        }

                        Switch(
                            checked = pomodoroMode,
                            onCheckedChange = { viewModel.togglePomodoroMode(it) },
                            modifier = Modifier.testTag("pomodoro_switch"),
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Black,
                                checkedTrackColor = Color.White,
                                uncheckedThumbColor = Color.Gray,
                                uncheckedTrackColor = Color(0xFF2C2C2C)
                            )
                        )
                    }

                    // Scroller / Custom Minutes selector when Pomodoro is OFF
                    if (!pomodoroMode) {
                        Divider(color = Color(0xFF2C2C2C))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = Localization.get(LocKey.FREE_FLOW, language).uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.5f),
                                letterSpacing = 2.sp
                            )
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = { if (freeFlowMinutes > 5) viewModel.updateSelectedFreeFlowMinutes(freeFlowMinutes - 5) }) {
                                    Text("-", color = Color.White, fontSize = 24.sp)
                                }
                                Text(
                                    text = "$freeFlowMinutes",
                                    style = MaterialTheme.typography.displayMedium,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 24.dp)
                                )
                                IconButton(onClick = { if (freeFlowMinutes < 180) viewModel.updateSelectedFreeFlowMinutes(freeFlowMinutes + 5) }) {
                                    Text("+", color = Color.White, fontSize = 24.sp)
                                }
                            }
                            Text(
                                text = Localization.get(LocKey.MINUTES, language),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    } else {
                        // Pomodoro Config (Work/Break)
                        Divider(color = Color(0xFF2C2C2C))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = Localization.get(LocKey.POMODORO_WORK, language),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "$pomodoroWorkMinutes ${Localization.get(LocKey.MIN_SUFFIX, language)}",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = Localization.get(LocKey.POMODORO_BREAK, language),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "$pomodoroBreakMinutes ${Localization.get(LocKey.MIN_SUFFIX, language)}",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    // START SESSION Button
                    Button(
                        onClick = { viewModel.startSession() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .testTag("start_session_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = Localization.get(LocKey.START_SESSION, language),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
            }

            // SECTION 3: Actionable Buttons (Export ID)
            OutlinedButton(
                onClick = { 
                    captureAndSaveCard(view, context, 
                        onSuccess = { Toast.makeText(context, Localization.get(LocKey.EXPORT_SUCCESS, language), Toast.LENGTH_SHORT).show() },
                        onFailure = { err -> Toast.makeText(context, "${Localization.get(LocKey.EXPORT_FAILURE, language)}: $err", Toast.LENGTH_SHORT).show() }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("export_id_button"),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
            ) {
                Text(
                    text = Localization.get(LocKey.EXPORT_ID, language),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// Helper function to capture the card as an image and save it to the gallery
private fun captureAndSaveCard(view: View, context: android.content.Context, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
    try {
        // Find the specific card view - this is a simplified version
        // In a real app, you might use a more robust way to target the specific Composable
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        // Save to gallery
        val filename = "FocusLedger_ID_${System.currentTimeMillis()}.png"
        val fos: FileOutputStream?
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            val resolver = context.contentResolver
            val contentValues = android.content.ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
            val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = imageUri?.let { resolver.openOutputStream(it) } as FileOutputStream?
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            onSuccess()
        } ?: onFailure("Output Stream Error")
    } catch (e: Exception) {
        onFailure(e.message ?: "Error")
    }
}

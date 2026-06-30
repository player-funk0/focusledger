package com.example.ui.screens

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.database.UserStatsEntity
import com.example.data.localization.LocKey
import com.example.data.localization.Localization
import com.example.ui.viewmodel.FocusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: FocusViewModel,
    onNavigateToProfile: () -> Unit,
    onNavigateToShop: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val language by viewModel.language.collectAsState()
    val autoDnd by viewModel.autoDnd.collectAsState()
    val dailyReminderEnabled by viewModel.dailyReminderEnabled.collectAsState()
    val dailyReminderTime by viewModel.dailyReminderTime.collectAsState()
    val userStats by viewModel.userStats.collectAsState()

    val stats = userStats ?: UserStatsEntity()

    var showLanguageDropdown by remember { mutableStateOf(false) }

    // DND check helper
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val hasDndPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        notificationManager.isNotificationPolicyAccessGranted
    } else {
        true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = Localization.get(LocKey.SETTINGS, language),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("settings_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = Localization.get(LocKey.BACK_DESC, language),
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

            // GROUP 1: Profile & Appearance
            Text(
                text = Localization.get(LocKey.PROFILE_APPEARANCE, language).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                letterSpacing = 1.sp
            )

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
                    // Edit Profile Nav Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToProfile() }
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Column {
                                Text(
                                    text = Localization.get(LocKey.EDIT_PROFILE, language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = Localization.get(LocKey.MANAGE_IDENTITY, language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }

                    Divider(color = Color(0xFF2C2C2C), modifier = Modifier.padding(horizontal = 20.dp))

                    // Customization Shop Nav Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToShop() }
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Column {
                                Text(
                                    text = Localization.get(LocKey.CUSTOMIZATION_SHOP, language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = Localization.get(LocKey.THEMES_VISUALS, language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }

            // GROUP 2: Notifications & DND Control
            Text(
                text = Localization.get(LocKey.NOTIFICATIONS_DND, language).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                letterSpacing = 1.sp
            )

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
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                    // Switch 1: Auto DND Mode
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = Localization.get(LocKey.AUTO_DND, language),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = Localization.get(LocKey.SYS_OVERRIDE, language),
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        Switch(
                            checked = autoDnd,
                            onCheckedChange = { isChecked ->
                                if (isChecked && !hasDndPermission) {
                                    // Request DND permission from user
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        try {
                                            context.startActivity(
                                                Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS).apply {
                                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                                }
                                            )
                                            Toast.makeText(
                                                context,
                                                Localization.get(LocKey.ERR_DND_PERMISSION, language),
                                                Toast.LENGTH_LONG
                                            ).show()
                                        } catch (e: Exception) {
                                            Toast.makeText(
                                                context,
                                                Localization.get(LocKey.ERR_SETTINGS_OPEN, language),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                } else {
                                    viewModel.toggleAutoDnd(isChecked)
                                }
                            },
                            modifier = Modifier.testTag("auto_dnd_switch"),
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Black,
                                checkedTrackColor = Color.White,
                                uncheckedThumbColor = Color.Gray,
                                uncheckedTrackColor = Color(0xFF2C2C2C)
                            )
                        )
                    }

                    Divider(color = Color(0xFF2C2C2C))

                    // Custom Music Selection
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        val musicLauncher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.OpenDocument()
                        ) { uri: Uri? ->
                            uri?.let {
                                try {
                                    context.contentResolver.takePersistableUriPermission(
                                        it,
                                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    )
                                    viewModel.setCustomMusic(it.toString())
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    // Fallback if permission fails
                                    viewModel.setCustomMusic(it.toString())
                                }
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = Localization.get(LocKey.CUSTOM_MUSIC, language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = if (stats.useCustomMusic && stats.customMusicUri != null) 
                                        "${Localization.get(LocKey.SELECTED_PREFIX, language)} ${stats.customMusicUri.substringAfterLast("/")}" 
                                        else Localization.get(LocKey.MUSIC_SUBTITLE, language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    maxLines = 1
                                )
                            }

                            Switch(
                                checked = stats.useCustomMusic,
                                onCheckedChange = { viewModel.toggleUseCustomMusic(it) },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.Black,
                                    checkedTrackColor = Color.White,
                                    uncheckedThumbColor = Color.Gray,
                                    uncheckedTrackColor = Color(0xFF2C2C2C)
                                )
                            )
                        }

                        Button(
                            onClick = { musicLauncher.launch(arrayOf("audio/*")) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White.copy(alpha = 0.1f),
                                contentColor = Color.White
                            ),
                            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
                        ) {
                            Text(Localization.get(LocKey.SELECT_MUSIC_FILE, language), fontSize = 12.sp)
                        }
                    }

                    Divider(color = Color(0xFF2C2C2C))

                    // Switch 2: Daily Reminder switch + inline Time Picker
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = Localization.get(LocKey.DAILY_REMINDER, language),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            val displayTime = remember(dailyReminderTime) {
                                try {
                                    val parts = dailyReminderTime.split(":")
                                    val h = parts[0].toInt()
                                    val m = parts[1].toInt()
                                    val amPm = if (h < 12) "AM" else "PM"
                                    val dh = when {
                                        h == 0 -> 12
                                        h > 12 -> h - 12
                                        else -> h
                                    }
                                    String.format("%02d:%02d %s", dh, m, amPm)
                                } catch (e: Exception) {
                                    dailyReminderTime
                                }
                            }
                            Text(
                                text = "${Localization.get(LocKey.SET_FOR, language)} $displayTime",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        Switch(
                            checked = dailyReminderEnabled,
                            onCheckedChange = { viewModel.setDailyReminderEnabled(it) },
                            modifier = Modifier.testTag("daily_reminder_switch"),
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Black,
                                checkedTrackColor = Color.White,
                                uncheckedThumbColor = Color.Gray,
                                uncheckedTrackColor = Color(0xFF2C2C2C)
                            )
                        )
                    }

                    AnimatedVisibility(visible = dailyReminderEnabled) {
                        // Interactive custom inline time picker
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF1A1A1A))
                                .border(1.dp, Color(0xFF2C2C2C), RoundedCornerShape(8.dp))
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            var hours by remember(dailyReminderTime) {
                                mutableStateOf(dailyReminderTime.split(":").getOrNull(0)?.toIntOrNull() ?: 8)
                            }
                            var minutes by remember(dailyReminderTime) {
                                mutableStateOf(dailyReminderTime.split(":").getOrNull(1)?.toIntOrNull() ?: 30)
                            }
                            var isAm by remember(dailyReminderTime) {
                                mutableStateOf(dailyReminderTime.split(":").getOrNull(0)?.toIntOrNull() ?: 8 < 12)
                            }

                            // Hours Picker (12-hour format)
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(onClick = { 
                                    hours = if (hours % 12 == 11) (if (isAm) 12 else 0) else (hours + 1) % 24 
                                }) {
                                    Icon(Icons.Default.KeyboardArrowUp, null, tint = Color.White)
                                }
                                val displayHour = when {
                                    hours == 0 -> 12
                                    hours > 12 -> hours - 12
                                    else -> hours
                                }
                                Text(
                                    text = String.format("%02d", displayHour),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                IconButton(onClick = { 
                                    hours = if (hours % 12 == 0) (if (isAm) 11 else 23) else hours - 1
                                }) {
                                    Icon(Icons.Default.KeyboardArrowDown, null, tint = Color.White)
                                }
                            }

                            Text(
                                text = ":",
                                fontSize = 24.sp,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )

                            // Minutes Picker
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(onClick = { minutes = (minutes + 5) % 60 }) {
                                    Icon(Icons.Default.KeyboardArrowUp, null, tint = Color.White)
                                }
                                Text(
                                    text = String.format("%02d", minutes),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                IconButton(onClick = { minutes = if (minutes < 5) 55 else minutes - 5 }) {
                                    Icon(Icons.Default.KeyboardArrowDown, null, tint = Color.White)
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            // AM/PM Picker
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = if (hours < 12) "AM" else "PM",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Color(0xFF2C2C2C))
                                        .clickable { 
                                            hours = if (hours < 12) hours + 12 else hours - 12
                                        }
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(24.dp))

                            Button(
                                onClick = {
                                    val formatted = String.format("%02d:%02d", hours, minutes)
                                    viewModel.setDailyReminderTime(formatted)
                                    val amPm = if (hours < 12) "AM" else "PM"
                                    val displayHour = when {
                                        hours == 0 -> 12
                                        hours > 12 -> hours - 12
                                        else -> hours
                                    }
                                    Toast.makeText(context, "Reminder time set to $displayHour:${String.format("%02d", minutes)} $amPm", Toast.LENGTH_SHORT).show()
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier.testTag("apply_time_button")
                            ) {
                                    Text(Localization.get(LocKey.SET_ACTION, language), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            // GROUP 3: Language Preference
            Text(
                text = Localization.get(LocKey.LANGUAGE_PREFERENCE, language).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                letterSpacing = 1.sp
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                border = BorderStroke(
                    1.dp,
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.02f)
                        )
                    )
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.06f),
                                    Color.White.copy(alpha = 0.01f)
                                )
                            )
                        )
                        .clickable { showLanguageDropdown = !showLanguageDropdown }
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = Localization.get(LocKey.DISPLAY_LANGUAGE, language),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = language,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    Icon(
                        imageVector = if (showLanguageDropdown) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                DropdownMenu(
                    expanded = showLanguageDropdown,
                    onDismissRequest = { showLanguageDropdown = false },
                    modifier = Modifier
                        .background(Color(0xFF1C1C1C))
                        .border(1.dp, Color(0xFF2C2C2C), RoundedCornerShape(8.dp))
                ) {
                    listOf("English", "العربية الفصحى", "العامية المصرية", "الفرانكو").forEach { style ->
                        DropdownMenuItem(
                            text = { Text(text = style, color = Color.White) },
                            onClick = {
                                viewModel.setDisplayLanguage(style)
                                showLanguageDropdown = false
                            },
                            modifier = Modifier.testTag("lang_$style")
                        )
                    }
                }
            }

            // GROUP 4: About
            Text(
                text = Localization.get(LocKey.ABOUT, language).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                letterSpacing = 1.sp
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                border = BorderStroke(
                    1.dp,
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.02f)
                        )
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.06f),
                                    Color.White.copy(alpha = 0.01f)
                                )
                            )
                        )
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Small lock visual
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(6.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Lock",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Text(
                        text = "FOCUSLEDGER",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        letterSpacing = 2.sp
                    )
                    Text(
                        text = Localization.get(LocKey.VERSION_LABEL, language),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = Localization.get(LocKey.PRIVACY_MSG, language),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 13.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Black.copy(alpha = 0.4f))
                            .padding(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
}

package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun ProfileScreen(
    viewModel: FocusViewModel,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val userStats by viewModel.userStats.collectAsState()
    val language by viewModel.language.collectAsState()

    val stats = userStats ?: UserStatsEntity()

    var username by remember(stats) { mutableStateOf(stats.username) }
    var bio by remember(stats) { mutableStateOf(stats.bio) }
    var selectedAvatarId by remember(stats) { mutableStateOf(stats.avatarNumber) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = Localization.get(LocKey.EDIT_PROFILE, language),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
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
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
            // Input: Username
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
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                    Text(
                        text = Localization.get(LocKey.USERNAME, language),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.6f)
                    )

                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color(0xFF2C2C2C)
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("username_input")
                    )
                }
            }

            // Input: Status/Bio
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
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                    Text(
                        text = Localization.get(LocKey.STATUS, language),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.6f)
                    )

                    TextField(
                        value = bio,
                        onValueChange = { bio = it },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color(0xFF2C2C2C)
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("status_input")
                    )
                }
            }

            // SECTION 3: Avatar Selection
            Text(
                text = Localization.get(LocKey.SELECT_AVATAR, language),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
                letterSpacing = 1.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                (0..4).forEach { id ->
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (selectedAvatarId == id) Color.White else Color(0xFF1C1C1C))
                            .border(
                                1.dp,
                                if (selectedAvatarId == id) Color.White else Color(0xFF2C2C2C),
                                RoundedCornerShape(12.dp)
                            )
                            .clickable { selectedAvatarId = id }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        GeometricAvatar(
                            avatarId = id,
                            modifier = Modifier.size(24.dp),
                            color = if (selectedAvatarId == id) Color.Black else Color.White
                        )
                    }
                }
            }

            // Secure storage explanatory text
            Text(
                text = Localization.get(LocKey.VAULT_SECURE_MSG, language),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 11.sp,
                color = Color.Gray,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Save Button
            Button(
                onClick = {
                    if (username.isBlank()) {
                        Toast.makeText(context, Localization.get(LocKey.ERROR_EMPTY_NAME, language), Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.updateProfile(username, bio, selectedAvatarId)
                        Toast.makeText(context, Localization.get(LocKey.PROFILE_SAVED, language), Toast.LENGTH_SHORT).show()
                        onNavigateBack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("save_profile_button"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = Localization.get(LocKey.SAVE_PROFILE, language).uppercase(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}
}

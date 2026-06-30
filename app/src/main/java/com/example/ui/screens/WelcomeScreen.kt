package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.LocKey
import com.example.data.localization.Localization
import com.example.ui.viewmodel.FocusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    viewModel: FocusViewModel
) {
    val language by viewModel.language.collectAsState()
    val defaultName = Localization.get(LocKey.DEFAULT_MEMBER_NAME, language)
    var nameInput by remember(language) { mutableStateOf(defaultName) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Brand Header
            Text(
                text = "FOCUSLEDGER",
                style = MaterialTheme.typography.labelSmall,
                letterSpacing = 4.sp,
                color = Color.White.copy(alpha = 0.5f)
            )

            Text(
                text = Localization.get(LocKey.WELCOME_TITLE, language),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Text(
                text = Localization.get(LocKey.WELCOME_SUBTITLE, language),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Features explaining the app
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                WelcomeFeatureRow(
                    icon = Icons.Default.Info,
                    title = Localization.get(LocKey.FEATURE_FOCUS_TITLE, language),
                    description = Localization.get(LocKey.FEATURE_FOCUS_DESC, language)
                )

                WelcomeFeatureRow(
                    icon = Icons.Default.List,
                    title = Localization.get(LocKey.FEATURE_LEDGER_TITLE, language),
                    description = Localization.get(LocKey.FEATURE_LEDGER_DESC, language)
                )

                WelcomeFeatureRow(
                    icon = Icons.Default.ShoppingCart,
                    title = Localization.get(LocKey.FEATURE_ECONOMY_TITLE, language),
                    description = Localization.get(LocKey.FEATURE_ECONOMY_DESC, language)
                )

                WelcomeFeatureRow(
                    icon = Icons.Default.Warning,
                    title = Localization.get(LocKey.FEATURE_PENALTY_TITLE, language),
                    description = Localization.get(LocKey.FEATURE_PENALTY_DESC, language)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Enter Name Card
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
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                    Text(
                        text = Localization.get(LocKey.CHOOSE_IDENTITY, language),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.6f),
                        letterSpacing = 1.sp
                    )

                    OutlinedTextField(
                        value = nameInput,
                        onValueChange = { if (it.length <= 25) nameInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("onboarding_name_input"),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                        placeholder = { Text(Localization.get(LocKey.ENTER_NAME_PLACEHOLDER, language), color = Color.Gray) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color(0xFF2C2C2C),
                            focusedContainerColor = Color(0xFF0E0E0E),
                            unfocusedContainerColor = Color(0xFF0E0E0E)
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                    )

                    Text(
                        text = Localization.get(LocKey.ONBOARDING_FOOTER, language),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Start Button
            Button(
                onClick = {
                    val finalName = nameInput.trim().ifEmpty { defaultName }
                    viewModel.updateProfile(finalName, Localization.get(LocKey.DEFAULT_STATUS, language), 0)
                    viewModel.setOnboardingCompleted(true)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("onboarding_start_button"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = Localization.get(LocKey.INITIALIZE_PROTOCOL, language),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun WelcomeFeatureRow(
    icon: ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF161616))
                .border(1.dp, Color(0xFF2C2C2C), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}

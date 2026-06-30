package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import com.example.data.database.UserStatsEntity
import com.example.data.localization.LocKey
import com.example.data.localization.Localization
import com.example.ui.theme.CustomizationStyles
import com.example.ui.viewmodel.FocusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(
    viewModel: FocusViewModel
) {
    val context = LocalContext.current
    val language by viewModel.language.collectAsState()
    val userStats by viewModel.userStats.collectAsState()

    val stats = userStats ?: UserStatsEntity()
    val activeFontFamily = CustomizationStyles.getFontFamily(stats.activeFont)

    // Unlocked collections parsed from CSV
    val unlockedGradients = remember(stats) {
        stats.unlockedGradients.split(",").map { it.trim() }
    }
    val unlockedFonts = remember(stats) {
        stats.unlockedFonts.split(",").map { it.trim() }
    }
    val unlockedTitles = remember(stats) {
        stats.unlockedTitles.split(",").map { it.trim() }
    }
    // unlockedSounds removed as per user request

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "FOCUSLEDGER",
                            style = MaterialTheme.typography.labelSmall,
                            letterSpacing = 2.sp,
                            color = Color.White
                        )

                        // Digital balance token pill
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFF121212))
                                .border(1.dp, Color(0xFF2C2C2C), RoundedCornerShape(20.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Points",
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "${stats.tokenBalance} FP",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
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

            // Shop Header Section
            Column {
                Text(
                    text = Localization.get(LocKey.SHOP_TITLE, language),
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = activeFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = Localization.get(LocKey.SHOP_SUBTITLE, language),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            // SECTION 1: Premium Gradients
            Text(
                text = Localization.get(LocKey.PREMIUM_GRADIENTS, language).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
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
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                    val gradients = listOf(
                        Triple(Localization.get(LocKey.GRADIENT_BRUSHED_TITANIUM, language), Localization.get(LocKey.DESC_BRUSHED_TITANIUM, language), 0),
                        Triple(Localization.get(LocKey.GRADIENT_CYBER_NEON, language), Localization.get(LocKey.DESC_CYBER_NEON, language), 500),
                        Triple(Localization.get(LocKey.GRADIENT_ROSE_QUARTZ, language), Localization.get(LocKey.DESC_ROSE_QUARTZ, language), 800),
                        Triple(Localization.get(LocKey.GRADIENT_MATTE_OBSIDIAN, language), Localization.get(LocKey.DESC_MATTE_OBSIDIAN, language), 1200),
                        Triple(Localization.get(LocKey.GRADIENT_EMERALD_MATRIX, language), Localization.get(LocKey.DESC_EMERALD_MATRIX, language), 1500),
                        Triple(Localization.get(LocKey.GRADIENT_SOLAR_FLARE, language), Localization.get(LocKey.DESC_SOLAR_FLARE, language), 2000),
                        Triple(Localization.get(LocKey.GRADIENT_GOLDEN_AURUM, language), Localization.get(LocKey.DESC_GOLDEN_AURUM, language), 3000)
                    )

                    gradients.forEach { (name, desc, price) ->
                        val isUnlocked = unlockedGradients.contains(name)
                        val isEquipped = stats.activeGradient == name

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .border(
                                    1.dp,
                                    if (isEquipped) Color.White else Color.Transparent,
                                    RoundedCornerShape(12.dp)
                                )
                                .background(Color(0xFF1C1C1C))
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Small gradient circular preview
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(CustomizationStyles.getGradientBrush(name)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = Localization.get(LocKey.PREVIEW, language).first().toString(),
                                        fontSize = 9.sp,
                                        color = Color.White.copy(alpha = 0.6f)
                                    )
                                }

                                Column {
                                    Text(
                                        text = name,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = desc,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }
                            }

                            // Buy or equip button
                            Button(
                                onClick = {
                                    if (isUnlocked) {
                                        viewModel.equipGradient(name)
                                        Toast.makeText(context, "$name ${Localization.get(LocKey.EQUIPPED, language)}", Toast.LENGTH_SHORT).show()
                                    } else {
                                        val success = viewModel.buyGradient(name, price)
                                        if (success) {
                                            Toast.makeText(
                                                context,
                                                "${Localization.get(LocKey.UNLOCKED_AND_EQUIPPED, language)} $name!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(context, Localization.get(LocKey.INSUFFICIENT_BALANCE, language), Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isEquipped) Color.White else Color(0xFF2C2C2C),
                                    contentColor = if (isEquipped) Color.Black else Color.White
                                ),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                modifier = Modifier.testTag("gradient_action_$name")
                            ) {
                                Text(
                                    text = if (isEquipped) {
                                        Localization.get(LocKey.EQUIPPED, language)
                                    } else if (isUnlocked) {
                                        Localization.get(LocKey.EQUIP_ACTION, language)
                                    } else {
                                        "$price FP"
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }

            // SECTION 2: Minimalist Typography
            Text(
                text = Localization.get(LocKey.MINIMALIST_TYPO, language).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
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
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                    val fonts = listOf(
                        Triple(Localization.get(LocKey.FONT_INTER_SANS, language), Localization.get(LocKey.DESC_INTER_SANS, language), 0),
                        Triple(Localization.get(LocKey.FONT_MONOSPACE_PRO, language), Localization.get(LocKey.DESC_MONOSPACE_PRO, language), 400),
                        Triple(Localization.get(LocKey.FONT_EXECUTIVE_SERIF, language), Localization.get(LocKey.DESC_EXECUTIVE_SERIF, language), 650)
                    )

                    fonts.forEach { (name, desc, price) ->
                        val isUnlocked = unlockedFonts.contains(name)
                        val isEquipped = stats.activeFont == name

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .border(
                                    1.dp,
                                    if (isEquipped) Color.White else Color.Transparent,
                                    RoundedCornerShape(12.dp)
                                )
                                .background(Color(0xFF1C1C1C))
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Typographic preview block
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.Black),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Aa",
                                        fontSize = 18.sp,
                                        fontFamily = CustomizationStyles.getFontFamily(name),
                                        fontWeight = CustomizationStyles.getFontWeight(name),
                                        color = Color.White
                                    )
                                }

                                Column {
                                    Text(
                                        text = name,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = desc,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }
                            }

                            Button(
                                onClick = {
                                    if (isUnlocked) {
                                        viewModel.equipFont(name)
                                        Toast.makeText(context, "$name ${Localization.get(LocKey.EQUIPPED, language)}", Toast.LENGTH_SHORT).show()
                                    } else {
                                        val success = viewModel.buyFont(name, price)
                                        if (success) {
                                            Toast.makeText(
                                                context,
                                                "${Localization.get(LocKey.UNLOCKED_AND_EQUIPPED, language)} $name!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(context, Localization.get(LocKey.INSUFFICIENT_BALANCE, language), Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isEquipped) Color.White else Color(0xFF2C2C2C),
                                    contentColor = if (isEquipped) Color.Black else Color.White
                                ),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                modifier = Modifier.testTag("font_action_$name")
                            ) {
                                Text(
                                    text = if (isEquipped) {
                                        Localization.get(LocKey.EQUIPPED, language)
                                    } else if (isUnlocked) {
                                        Localization.get(LocKey.EQUIP_ACTION, language)
                                    } else {
                                        "$price FP"
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }

            // SECTION 3: Status Titles
            Text(
                text = Localization.get(LocKey.STATUS_TITLES, language).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
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
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                    val titles = listOf(
                        Triple(Localization.get(LocKey.TITLE_CASUAL, language), Localization.get(LocKey.DESC_CASUAL, language), 0),
                        Triple(Localization.get(LocKey.TITLE_DEEP_WORK_MASTER, language), Localization.get(LocKey.DESC_DEEP_WORK_MASTER, language), 1500),
                        Triple(Localization.get(LocKey.TITLE_ELITE, language), Localization.get(LocKey.DESC_ELITE, language), 5000)
                    )

                    titles.forEach { (name, desc, price) ->
                        val isUnlocked = unlockedTitles.contains(name)
                        val isEquipped = stats.activeTitle == name

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .border(
                                    1.dp,
                                    if (isEquipped) Color.White else Color.Transparent,
                                    RoundedCornerShape(12.dp)
                                )
                                .background(Color(0xFF1C1C1C))
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = name,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                                Text(
                                    text = desc,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }

                            Button(
                                onClick = {
                                    if (isUnlocked) {
                                        viewModel.equipTitle(name)
                                        Toast.makeText(context, "$name ${Localization.get(LocKey.EQUIPPED, language)}", Toast.LENGTH_SHORT).show()
                                    } else {
                                        val success = viewModel.buyTitle(name, price)
                                        if (success) {
                                            Toast.makeText(
                                                context,
                                                "${Localization.get(LocKey.UNLOCKED_AND_EQUIPPED, language)} $name!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(context, Localization.get(LocKey.INSUFFICIENT_BALANCE, language), Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isEquipped) Color.White else Color(0xFF2C2C2C),
                                    contentColor = if (isEquipped) Color.Black else Color.White
                                ),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                modifier = Modifier.testTag("title_action_$name")
                            ) {
                                Text(
                                    text = if (isEquipped) {
                                        Localization.get(LocKey.EQUIPPED, language)
                                    } else if (isUnlocked) {
                                        Localization.get(LocKey.EQUIP_ACTION, language)
                                    } else {
                                        "$price FP"
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }

            // SECTION 4: Premium Alarm Sounds removed as per user request (replaced with calm music)

            // Decorative coming soon atmospheric card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.06f),
                                    Color.White.copy(alpha = 0.01f)
                                )
                            )
                        )
                ) {
                    // Geometric abstract 2D graphics
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White)
                            .align(Alignment.Center)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = Localization.get(LocKey.COMING_SOON, language).uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = Localization.get(LocKey.ZEN_PACK, language),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
}

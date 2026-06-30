package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

object CustomizationStyles {
    fun getGradientBrush(name: String): Brush {
        return when (name) {
            "Cyber Neon" -> Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1E083C), // Deep violet
                    Color(0xFF0B0114)  // Dark obsidian
                )
            )
            "Matte Obsidian" -> Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF161616), // Dim charcoal
                    Color(0xFF050505)  // Extreme black
                )
            )
            "Rose Quartz" -> Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF8B263E), // Deep warm rose-pink
                    Color(0xFF2C0B14)  // Luxury dark ruby obsidian
                )
            )
            "Emerald Matrix" -> Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF003B26), // Deep digital emerald green
                    Color(0xFF020F0B)  // Dark matrix void
                )
            )
            "Solar Flare" -> Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF6B2D00), // Glowing amber solar orange
                    Color(0xFF1F0D00)  // Deep cosmic stellar shadow
                )
            )
            "Golden Aurum" -> Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF635216), // Liquid matte gold
                    Color(0xFF1E1805)  // Royal golden shadow
                )
            )
            // Brushed Titanium or default
            else -> Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF383838), // Cool steel gray
                    Color(0xFF1A1A1A)  // Dark titanium
                )
            )
        }
    }

    fun getFontFamily(name: String): FontFamily {
        return when (name) {
            "Monospace Pro" -> FontFamily.Monospace
            "Executive Serif" -> FontFamily.Serif
            else -> FontFamily.Default // Inter Sans equivalent
        }
    }

    fun getFontWeight(name: String): FontWeight {
        return when (name) {
            "Executive Serif" -> FontWeight.Normal
            else -> FontWeight.Light
        }
    }

    // Apple Liquid Glass Effect Modifiers
    val LiquidGlassModifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .background(
            Brush.verticalGradient(
                colors = listOf(
                    Color.White.copy(alpha = 0.08f),
                    Color.White.copy(alpha = 0.02f)
                )
            )
        )
        .padding(1.dp) // Space for the refractive border

    val LiquidGlassBorder = BorderStroke(
        width = 1.dp,
        brush = Brush.verticalGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.2f),
                Color.White.copy(alpha = 0.05f)
            )
        )
    )

    val LiquidGlassContainerColor = Color.Transparent
}

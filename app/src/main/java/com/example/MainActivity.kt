package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.FocusViewModel
import com.example.ui.viewmodel.TimerState
import kotlinx.coroutines.launch

sealed class Screen {
    object MainApp : Screen()
    object Settings : Screen()
    object Profile : Screen()
}

enum class MainTab {
    Home,
    Ledger,
    Shop
}

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: FocusViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = ViewModelProvider(this).get(FocusViewModel::class.java)

        setContent {
            MyApplicationTheme {
                val timerState by viewModel.timerState.collectAsState()
                val onboardingCompleted by viewModel.onboardingCompleted.collectAsState()

                if (!onboardingCompleted) {
                    WelcomeScreen(viewModel = viewModel)
                } else if (timerState == TimerState.FOCUSING || timerState == TimerState.BREAK) {
                    // Screen Two: Deep Focus Screen overrides the entire application interface
                    FocusScreen(viewModel = viewModel)
                } else {
                    // Tabbed / Navigated standard flow
                    var currentScreen by remember { mutableStateOf<Screen>(Screen.MainApp) }
                    var currentTab by remember { mutableStateOf(MainTab.Home) }

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        AnimatedContent(
                            targetState = currentScreen,
                            transitionSpec = {
                                if (targetState is Screen.Settings || targetState is Screen.Profile) {
                                    (slideInHorizontally(animationSpec = tween(300)) { it } + fadeIn(animationSpec = tween(300)))
                                        .togetherWith(slideOutHorizontally(animationSpec = tween(300)) { -it / 2 } + fadeOut(animationSpec = tween(300)))
                                } else {
                                    (slideInHorizontally(animationSpec = tween(300)) { -it / 2 } + fadeIn(animationSpec = tween(300)))
                                        .togetherWith(slideOutHorizontally(animationSpec = tween(300)) { it } + fadeOut(animationSpec = tween(300)))
                                }
                            },
                            label = "ScreenTransition"
                        ) { targetScreen ->
                            when (targetScreen) {
                                is Screen.Settings -> {
                                    SettingsScreen(
                                        viewModel = viewModel,
                                        onNavigateToProfile = { currentScreen = Screen.Profile },
                                        onNavigateToShop = {
                                            currentScreen = Screen.MainApp
                                            currentTab = MainTab.Shop
                                        },
                                        onNavigateBack = { currentScreen = Screen.MainApp }
                                    )
                                }
                                is Screen.Profile -> {
                                    ProfileScreen(
                                        viewModel = viewModel,
                                        onNavigateBack = { currentScreen = Screen.Settings }
                                    )
                                }
                                is Screen.MainApp -> {
                                    Scaffold(
                                        bottomBar = {
                                            NavigationBar(
                                                containerColor = CustomizationStyles.LiquidGlassContainerColor,
                                                tonalElevation = 0.dp,
                                                modifier = Modifier.border(
                                                    BorderStroke(
                                                        1.dp,
                                                        Brush.verticalGradient(
                                                            colors = listOf(
                                                                Color.White.copy(alpha = 0.15f),
                                                                Color.Transparent
                                                            )
                                                        )
                                                    )
                                                )
                                            ) {
                                                NavigationBarItem(
                                                    selected = currentTab == MainTab.Home,
                                                    onClick = { currentTab = MainTab.Home },
                                                    icon = { Icon(Icons.Default.Home, contentDescription = Localization.get(LocKey.NAV_HOME, language), modifier = Modifier.size(20.dp)) },
                                                    label = { Text(Localization.get(LocKey.NAV_HOME, language), fontSize = 11.sp) },
                                                    colors = NavigationBarItemDefaults.colors(
                                                        selectedIconColor = Color.White,
                                                        unselectedIconColor = Color.Gray,
                                                        selectedTextColor = Color.White,
                                                        unselectedTextColor = Color.Gray,
                                                        indicatorColor = Color(0xFF2C2C2C)
                                                    )
                                                 )
                                                 NavigationBarItem(
                                                    selected = currentTab == MainTab.Ledger,
                                                    onClick = { currentTab = MainTab.Ledger },
                                                    icon = { Icon(Icons.Default.List, contentDescription = Localization.get(LocKey.NAV_LEDGER, language), modifier = Modifier.size(20.dp)) },
                                                    label = { Text(Localization.get(LocKey.NAV_LEDGER, language), fontSize = 11.sp) },
                                                    colors = NavigationBarItemDefaults.colors(
                                                        selectedIconColor = Color.White,
                                                        unselectedIconColor = Color.Gray,
                                                        selectedTextColor = Color.White,
                                                        unselectedTextColor = Color.Gray,
                                                        indicatorColor = Color(0xFF2C2C2C)
                                                    )
                                                 )
                                                 NavigationBarItem(
                                                    selected = currentTab == MainTab.Shop,
                                                    onClick = { currentTab = MainTab.Shop },
                                                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = Localization.get(LocKey.NAV_SHOP, language), modifier = Modifier.size(20.dp)) },
                                                    label = { Text(Localization.get(LocKey.NAV_SHOP, language), fontSize = 11.sp) },
                                                    colors = NavigationBarItemDefaults.colors(
                                                        selectedIconColor = Color.White,
                                                        unselectedIconColor = Color.Gray,
                                                        selectedTextColor = Color.White,
                                                        unselectedTextColor = Color.Gray,
                                                        indicatorColor = Color(0xFF2C2C2C)
                                                    )
                                                 )
                                            }
                                        },
                                        containerColor = Color.Black
                                    ) { innerPadding ->
                                        Box(modifier = Modifier.padding(innerPadding)) {
                                            AnimatedContent(
                                                targetState = currentTab,
                                                transitionSpec = {
                                                    fadeIn(animationSpec = tween(200)) togetherWith fadeOut(animationSpec = tween(200))
                                                },
                                                label = "TabTransition"
                                            ) { targetTab ->
                                                when (targetTab) {
                                                    MainTab.Home -> HomeScreen(
                                                        viewModel = viewModel,
                                                        onNavigateToSettings = { currentScreen = Screen.Settings },
                                                        onNavigateToProfile = { currentScreen = Screen.Profile }
                                                    )
                                                    MainTab.Ledger -> LedgerScreen(viewModel = viewModel)
                                                    MainTab.Shop -> ShopScreen(viewModel = viewModel)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.onAppPaused()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onAppResumed()
    }
}

package com.janaushadhi.finder.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.janaushadhi.finder.navigation.Screen
import com.janaushadhi.finder.navigation.bottomNavItems
import com.janaushadhi.finder.ui.auth.LoginScreen
import com.janaushadhi.finder.ui.auth.SplashScreen
import com.janaushadhi.finder.ui.chat.AIChatScreen
import com.janaushadhi.finder.ui.home.HomeScreen
import com.janaushadhi.finder.ui.maps.MapsScreen
import com.janaushadhi.finder.ui.profile.ProfileScreen
import com.janaushadhi.finder.ui.search.SearchScreen
import com.janaushadhi.finder.ui.upload.PrescriptionUploadScreen
import com.janaushadhi.finder.ui.features.StockReminderScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    val isFullScreen = currentDestination?.route in listOf(Screen.Splash.route, Screen.Login.route)

    Scaffold(
        bottomBar = {
            if (!isFullScreen) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                        NavigationBarItem(
                            icon = { 
                                Icon(
                                    if (selected) screen.selectedIcon else screen.unselectedIcon, 
                                    contentDescription = null 
                                ) 
                            },
                            label = { Text(screen.title) },
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController, 
            startDestination = Screen.Splash.route, 
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onNavigateToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    },
                    onNavigateToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginSuccess = { 
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToSearch = { navController.navigate(Screen.Search.route) },
                    onNavigateToAI = { navController.navigate(Screen.AIChat.route) },
                    onNavigateToPrescription = { navController.navigate(Screen.PrescriptionUpload.route) },
                    onNavigateToMaps = { navController.navigate(Screen.Maps.route) },
                    onNavigateToTools = { navController.navigate(Screen.StockReminder.route) }
                )
            }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Maps.route) { MapsScreen() }
            composable(Screen.AIChat.route) { AIChatScreen() }
            composable(Screen.Profile.route) { 
                ProfileScreen(
                    onLogout = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                ) 
            }
            composable(Screen.PrescriptionUpload.route) { PrescriptionUploadScreen() }
            composable(Screen.StockReminder.route) { StockReminderScreen() }
        }
    }
}

package com.janaushadhi.finder.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : Screen(
        route = "home",
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object Search : Screen(
        route = "search",
        title = "Search",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search
    )

    object Maps : Screen(
        route = "maps",
        title = "Maps",
        selectedIcon = Icons.Filled.Place,
        unselectedIcon = Icons.Outlined.Place
    )

    object AIChat : Screen(
        route = "ai_chat",
        title = "AI Help",
        selectedIcon = Icons.Filled.AutoAwesome,
        unselectedIcon = Icons.Outlined.AutoAwesome
    )

    object Profile : Screen(
        route = "profile",
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    object PrescriptionUpload : Screen(
        route = "prescription_upload",
        title = "Upload",
        selectedIcon = Icons.Filled.CloudUpload,
        unselectedIcon = Icons.Outlined.CloudUpload
    )

    object Splash : Screen(
        route = "splash",
        title = "Splash",
        selectedIcon = Icons.Filled.Face,
        unselectedIcon = Icons.Outlined.Face
    )

    object Login : Screen(
        route = "login",
        title = "Login",
        selectedIcon = Icons.Filled.Login,
        unselectedIcon = Icons.Outlined.Login
    )

    object Signup : Screen(
        route = "signup",
        title = "Signup",
        selectedIcon = Icons.Filled.PersonAdd,
        unselectedIcon = Icons.Outlined.PersonAdd
    )

    object ForgotPassword : Screen(
        route = "forgot_password",
        title = "Forgot Password",
        selectedIcon = Icons.Filled.LockReset,
        unselectedIcon = Icons.Outlined.LockReset
    )

    object StockReminder : Screen(
        route = "stock_reminder",
        title = "Tools",
        selectedIcon = Icons.Filled.Assignment,
        unselectedIcon = Icons.Outlined.Assignment
    )
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Maps,
    Screen.AIChat,
    Screen.Profile
)

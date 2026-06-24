package com.raka.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raka.myapp.navigation.Screen
import com.raka.myapp.ui.screens.*
import com.raka.myapp.ui.theme.MYAPPTheme
import com.raka.myapp.ui.theme.AppleBlue
import com.raka.myapp.ui.theme.AppleCardBackground
import com.raka.myapp.ui.theme.AppleTextSecondary
import com.raka.myapp.viewmodel.NotesViewModel
import com.raka.myapp.viewmodel.TimeTrackerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MYAPPTheme {
                DevPortfolioApp()
            }
        }
    }
}

@Composable
fun DevPortfolioApp() {
    val navController = rememberNavController()
    val notesViewModel: NotesViewModel = viewModel()
    val timeTrackerViewModel: TimeTrackerViewModel = viewModel()
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // Screens that should show bottom navigation
    val screensWithBottomNav = listOf(
        Screen.Home.route,
        Screen.About.route,
        Screen.Projects.route,
        Screen.Notes.route,
        Screen.TimeTracker.route,
        Screen.Settings.route
    )
    
    Scaffold(
        bottomBar = {
            if (currentRoute in screensWithBottomNav) {
                BottomNavigationBar(navController = navController, currentRoute = currentRoute)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }
            
            composable(Screen.About.route) {
                AboutScreen()
            }
            
            composable(Screen.Projects.route) {
                ProjectsScreen()
            }
            
            composable(Screen.Notes.route) {
                NotesScreen(
                    navController = navController,
                    viewModel = notesViewModel
                )
            }
            
            composable(Screen.TimeTracker.route) {
                TimeTrackerScreen(
                    viewModel = timeTrackerViewModel
                )
            }
            
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
            
            composable("${Screen.AddEditNote.route}/{noteId}") {
                AddEditNoteScreen(
                    navController = navController,
                    viewModel = notesViewModel
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?
) {
    NavigationBar(
        containerColor = AppleCardBackground,
        tonalElevation = 8.dp
    ) {
        val items = listOf(
            BottomNavItem(
                route = Screen.Home.route,
                icon = Icons.Default.Home,
                label = "Home"
            ),
            BottomNavItem(
                route = Screen.Projects.route,
                icon = Icons.Default.Folder,
                label = "Projects"
            ),
            BottomNavItem(
                route = Screen.Notes.route,
                icon = Icons.Default.Note,
                label = "Notes"
            ),
            BottomNavItem(
                route = Screen.TimeTracker.route,
                icon = Icons.Default.Timer,
                label = "Timer"
            ),
            BottomNavItem(
                route = Screen.Settings.route,
                icon = Icons.Default.Settings,
                label = "Settings"
            )
        )
        
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppleBlue,
                    selectedTextColor = AppleBlue,
                    unselectedIconColor = AppleTextSecondary,
                    unselectedTextColor = AppleTextSecondary,
                    indicatorColor = AppleBlue.copy(alpha = 0.1f)
                )
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

package com.raka.myapp.navigation

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object About : Screen("about", "About")
    object Projects : Screen("projects", "Projects")
    object Notes : Screen("notes", "Notes")
    object TimeTracker : Screen("time_tracker", "Time Tracker")
    object Settings : Screen("settings", "Settings")
    object AddEditNote : Screen("add_edit_note", "Note")
}

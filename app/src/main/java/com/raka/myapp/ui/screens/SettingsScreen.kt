package com.raka.myapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raka.myapp.ui.components.AppleCard
import com.raka.myapp.ui.theme.*

@Composable
fun SettingsScreen() {
    val scrollState = rememberScrollState()
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppleBackground)
    ) {
        // Header
        Surface(
            color = AppleCardBackground,
            shadowElevation = 2.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Settings",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppleTextPrimary
                )
            }
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            // App Settings
            Text(
                text = "App Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppleTextPrimary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            AppleCard(
                backgroundColor = AppleCardBackground
            ) {
                Column {
                    SettingToggleItem(
                        icon = Icons.Default.Notifications,
                        title = "Notifications",
                        description = "Enable push notifications",
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                    
                    Divider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = AppleDivider
                    )
                    
                    SettingToggleItem(
                        icon = Icons.Default.DarkMode,
                        title = "Dark Mode",
                        description = "Coming soon...",
                        checked = darkModeEnabled,
                        onCheckedChange = { darkModeEnabled = it },
                        enabled = false
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // About App
            Text(
                text = "About App",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppleTextPrimary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            AppleCard(
                backgroundColor = AppleCardBackground
            ) {
                Column {
                    SettingInfoItem(
                        icon = Icons.Default.Info,
                        title = "Version",
                        value = "1.0.0"
                    )
                    
                    Divider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = AppleDivider
                    )
                    
                    SettingInfoItem(
                        icon = Icons.Default.Code,
                        title = "Developer",
                        value = "Sandip Gautam"
                    )
                    
                    Divider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = AppleDivider
                    )
                    
                    SettingInfoItem(
                        icon = Icons.Default.Build,
                        title = "Built with",
                        value = "Jetpack Compose"
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Data Management
            Text(
                text = "Data Management",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppleTextPrimary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            var showClearDialog by remember { mutableStateOf(false) }
            
            AppleCard(
                onClick = { showClearDialog = true },
                backgroundColor = AppleRed.copy(alpha = 0.1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = AppleRed,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Clear All Data",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppleRed
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Delete all notes and time entries",
                            fontSize = 14.sp,
                            color = AppleTextSecondary
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = AppleRed,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            // Clear Data Dialog
            if (showClearDialog) {
                AlertDialog(
                    onDismissRequest = { showClearDialog = false },
                    icon = {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = null,
                            tint = AppleRed
                        )
                    },
                    title = {
                        Text(
                            "Clear All Data?",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    text = {
                        Text("This will permanently delete all your notes and time tracking history. This action cannot be undone.")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                // Clear data logic here
                                showClearDialog = false
                            },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = AppleRed
                            )
                        ) {
                            Text("Clear Data", fontWeight = FontWeight.Bold)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showClearDialog = false }) {
                            Text("Cancel", fontWeight = FontWeight.Medium)
                        }
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Footer
            Text(
                text = "Made with ❤️ by Sandip Gautam",
                fontSize = 14.sp,
                color = AppleTextSecondary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "DevPortfolio Hub © 2026",
                fontSize = 12.sp,
                color = AppleTextSecondary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SettingToggleItem(
    icon: ImageVector,
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) AppleBlue else AppleTextSecondary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (enabled) AppleTextPrimary else AppleTextSecondary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                color = AppleTextSecondary
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AppleCardBackground,
                checkedTrackColor = AppleBlue,
                uncheckedThumbColor = AppleCardBackground,
                uncheckedTrackColor = AppleTextSecondary
            )
        )
    }
}

@Composable
fun SettingInfoItem(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AppleBlue,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = AppleTextPrimary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 15.sp,
            color = AppleTextSecondary
        )
    }
}

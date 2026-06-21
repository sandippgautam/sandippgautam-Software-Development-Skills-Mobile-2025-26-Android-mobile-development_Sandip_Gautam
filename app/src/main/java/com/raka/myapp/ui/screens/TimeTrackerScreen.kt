package com.raka.myapp.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raka.myapp.data.ActivityType
import com.raka.myapp.data.TimeEntry
import com.raka.myapp.ui.components.AppleButton
import com.raka.myapp.ui.components.AppleCard
import com.raka.myapp.ui.theme.*
import com.raka.myapp.viewmodel.TimeTrackerViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeTrackerScreen(
    viewModel: TimeTrackerViewModel = viewModel()
) {
    var selectedActivity by remember { mutableStateOf(ActivityType.CODING.displayName) }
    var currentDuration by remember { mutableStateOf(0L) }
    val isTracking by remember { derivedStateOf { viewModel.isTracking } }
    
    // Update timer every second when tracking
    LaunchedEffect(isTracking) {
        while (isTracking) {
            currentDuration = viewModel.getCurrentDuration()
            delay(1000)
        }
    }
    
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
                    text = "Time Tracker",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppleTextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Track your productivity",
                    fontSize = 16.sp,
                    color = AppleTextSecondary
                )
            }
        }
        
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Timer Card
            item {
                AppleCard(
                    backgroundColor = AppleBlue.copy(alpha = 0.1f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Timer Display
                        Box(
                            modifier = Modifier
                                .size(180.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isTracking) AppleBlue.copy(alpha = 0.15f)
                                    else AppleBackground
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = formatDuration(if (isTracking) currentDuration else 0),
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isTracking) AppleBlue else AppleTextSecondary
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Activity Dropdown
                        if (!isTracking) {
                            Text(
                                text = "Select Activity",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = AppleTextSecondary
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            var expanded by remember { mutableStateOf(false) }
                            
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = it }
                            ) {
                                TextField(
                                    value = selectedActivity,
                                    onValueChange = {},
                                    readOnly = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                    trailingIcon = {
                                        Icon(
                                            Icons.Default.ArrowDropDown,
                                            contentDescription = null
                                        )
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = AppleCardBackground,
                                        unfocusedContainerColor = AppleCardBackground,
                                        focusedIndicatorColor = AppleBlue,
                                        unfocusedIndicatorColor = AppleDivider
                                    )
                                )
                                
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    ActivityType.values().forEach { activity ->
                                        DropdownMenuItem(
                                            text = { Text(activity.displayName) },
                                            onClick = {
                                                selectedActivity = activity.displayName
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(16.dp))
                        } else {
                            Text(
                                text = selectedActivity,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = AppleBlue
                            )
                            
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        
                        // Start/Stop Button
                        AppleButton(
                            text = if (isTracking) "Stop" else "Start",
                            onClick = {
                                if (isTracking) {
                                    viewModel.stopTracking()
                                    currentDuration = 0
                                } else {
                                    viewModel.startTracking(selectedActivity)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            containerColor = if (isTracking) AppleRed else AppleBlue
                        )
                    }
                }
            }
            
            // Statistics
            item {
                Text(
                    text = "Statistics",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppleTextPrimary
                )
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(
                        title = "Total Time",
                        value = formatDuration(viewModel.getTotalTime()),
                        icon = Icons.Default.Timer,
                        color = AppleOrange,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Sessions",
                        value = "${viewModel.entries.size}",
                        icon = Icons.Default.ListAlt,
                        color = ApplePurple,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            
            // History
            if (viewModel.entries.isNotEmpty()) {
                item {
                    Text(
                        text = "Recent Sessions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppleTextPrimary
                    )
                }
                
                items(viewModel.entries) { entry ->
                    TimeEntryCard(entry = entry)
                }
            }
        }
    }
}

@Composable
fun TimeEntryCard(entry: TimeEntry) {
    val activityColor = when (entry.activity) {
        ActivityType.CODING.displayName -> AppleBlue
        ActivityType.LEARNING.displayName -> AppleGreen
        ActivityType.MEETING.displayName -> AppleOrange
        ActivityType.BREAK.displayName -> ApplePurple
        else -> AppleLightBlue
    }
    
    AppleCard(
        backgroundColor = AppleCardBackground
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(activityColor)
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = entry.activity,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppleTextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = formatTimestamp(entry.startTime),
                        fontSize = 13.sp,
                        color = AppleTextSecondary
                    )
                }
            }
            
            Text(
                text = formatDuration(entry.duration),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = activityColor
            )
        }
    }
}

fun formatDuration(millis: Long): String {
    val seconds = (millis / 1000) % 60
    val minutes = (millis / (1000 * 60)) % 60
    val hours = millis / (1000 * 60 * 60)
    
    return if (hours > 0) {
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } else {
        String.format("%02d:%02d", minutes, seconds)
    }
}

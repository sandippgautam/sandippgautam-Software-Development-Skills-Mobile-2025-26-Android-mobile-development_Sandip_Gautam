package com.raka.myapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raka.myapp.ui.components.AppleCard
import com.raka.myapp.ui.theme.*
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    
    // Greeting animation
    var greetingVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        greetingVisible = true
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppleBackground)
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        
        // Profile Section with Avatar
        ProfileHeader()
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Quick Stats Cards
        Text(
            text = "Quick Stats",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AppleTextPrimary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "Projects",
                value = "12",
                icon = Icons.Default.Code,
                color = AppleBlue,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = "Notes",
                value = "24",
                icon = Icons.Default.Edit,
                color = AppleGreen,
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "Hours",
                value = "156",
                icon = Icons.Default.Timer,
                color = AppleOrange,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = "Skills",
                value = "18",
                icon = Icons.Default.Star,
                color = ApplePurple,
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Navigation Cards
        Text(
            text = "Explore",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AppleTextPrimary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        NavigationCard(
            title = "About Me",
            description = "Learn more about my background and skills",
            icon = Icons.Default.Person,
            color = AppleBlue
        ) {
            navController.navigate("about")
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        NavigationCard(
            title = "My Projects",
            description = "Check out my latest work and creations",
            icon = Icons.Default.Folder,
            color = ApplePurple
        ) {
            navController.navigate("projects")
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        NavigationCard(
            title = "Smart Notes",
            description = "Organize your thoughts and ideas",
            icon = Icons.Default.Note,
            color = AppleGreen
        ) {
            navController.navigate("notes")
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        NavigationCard(
            title = "Time Tracker",
            description = "Track your productivity and activities",
            icon = Icons.Default.AccessTime,
            color = AppleOrange
        ) {
            navController.navigate("time_tracker")
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ProfileHeader() {
    AppleCard(
        backgroundColor = AppleCardBackground
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(AppleBlue),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "SG",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Sandip Gautam",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppleTextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Android Developer",
                    fontSize = 16.sp,
                    color = AppleTextSecondary
                )
            }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    AppleCard(
        modifier = modifier,
        backgroundColor = color.copy(alpha = 0.1f)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = title,
            fontSize = 14.sp,
            color = AppleTextSecondary
        )
    }
}

@Composable
fun NavigationCard(
    title: String,
    description: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    AppleCard(
        onClick = onClick,
        backgroundColor = AppleCardBackground
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(26.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppleTextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = AppleTextSecondary
                )
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = AppleTextSecondary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

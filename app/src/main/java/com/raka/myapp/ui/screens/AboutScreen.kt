package com.raka.myapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun AboutScreen() {
    val scrollState = rememberScrollState()
    
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
                    text = "About Me",
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
            // Profile
            AppleCard(
                backgroundColor = AppleCardBackground
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(AppleBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "SG",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Sandip Gautam",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppleTextPrimary
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Android Developer • Tech Enthusiast",
                        fontSize = 16.sp,
                        color = AppleTextSecondary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Bio
            Text(
                text = "Bio",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppleTextPrimary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            AppleCard(
                backgroundColor = AppleCardBackground
            ) {
                Text(
                    text = "Passionate Android developer with expertise in building modern, user-friendly mobile applications. Experienced in Kotlin, Jetpack Compose, and following Material Design principles. Always eager to learn new technologies and solve challenging problems.",
                    fontSize = 15.sp,
                    color = AppleTextSecondary,
                    lineHeight = 24.sp
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Skills
            Text(
                text = "Technical Skills",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppleTextPrimary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            val skills = listOf(
                "Kotlin" to AppleBlue,
                "Jetpack Compose" to AppleGreen,
                "Android SDK" to AppleOrange,
                "Material Design" to ApplePurple,
                "MVVM Architecture" to AppleBlue,
                "Room Database" to AppleGreen,
                "Retrofit & APIs" to AppleOrange,
                "Git & GitHub" to ApplePurple,
                "Firebase" to AppleBlue,
                "Coroutines" to AppleGreen
            )
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                skills.chunked(2).forEach { rowSkills ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowSkills.forEach { (skill, color) ->
                            SkillChip(
                                skill = skill,
                                color = color,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Education
            Text(
                text = "Education",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppleTextPrimary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            AppleCard(
                backgroundColor = AppleCardBackground
            ) {
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(AppleBlue.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = AppleBlue,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    Column {
                        Text(
                            text = "LUT University",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppleTextPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Mobile Development Course",
                            fontSize = 15.sp,
                            color = AppleTextSecondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "2025 - 2026",
                            fontSize = 14.sp,
                            color = AppleTextSecondary
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Contact
            Text(
                text = "Contact",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppleTextPrimary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            ContactItem(
                icon = Icons.Default.Email,
                text = "sandip.gautam@example.com",
                color = AppleBlue
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            ContactItem(
                icon = Icons.Default.Code,
                text = "github.com/sandipgautam",
                color = ApplePurple
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            ContactItem(
                icon = Icons.Default.Work,
                text = "linkedin.com/in/sandipgautam",
                color = AppleBlue
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SkillChip(
    skill: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = color.copy(alpha = 0.12f)
    ) {
        Text(
            text = skill,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = color,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        )
    }
}

@Composable
fun ContactItem(
    icon: ImageVector,
    text: String,
    color: Color
) {
    AppleCard(
        backgroundColor = AppleCardBackground
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontSize = 15.sp,
                color = AppleTextPrimary
            )
        }
    }
}

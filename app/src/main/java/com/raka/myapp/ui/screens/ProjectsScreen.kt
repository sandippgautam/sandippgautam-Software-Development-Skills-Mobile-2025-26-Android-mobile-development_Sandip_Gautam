package com.raka.myapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Launch
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raka.myapp.data.Project
import com.raka.myapp.data.ProjectData
import com.raka.myapp.ui.components.AppleCard
import com.raka.myapp.ui.theme.*

@Composable
fun ProjectsScreen() {
    val projects = ProjectData.getSampleProjects()
    
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
                    text = "My Projects",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppleTextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${projects.size} Projects",
                    fontSize = 16.sp,
                    color = AppleTextSecondary
                )
            }
        }
        
        // Projects List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(projects) { project ->
                ProjectCard(project = project)
            }
        }
    }
}

@Composable
fun ProjectCard(project: Project) {
    AppleCard(
        backgroundColor = AppleCardBackground
    ) {
        // Project Icon
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(AppleBlue.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Code,
                contentDescription = null,
                tint = AppleBlue,
                modifier = Modifier.size(32.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Project Name
        Text(
            text = project.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppleTextPrimary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Project Description
        Text(
            text = project.description,
            fontSize = 15.sp,
            color = AppleTextSecondary,
            lineHeight = 22.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Tech Stack
        Text(
            text = "Tech Stack",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppleTextSecondary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Tech Tags
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            project.techStack.take(3).forEach { tech ->
                TechTag(tech = tech)
            }
        }
        
        if (project.techStack.size > 3) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                project.techStack.drop(3).forEach { tech ->
                    TechTag(tech = tech)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // GitHub Link
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Launch,
                contentDescription = null,
                tint = AppleBlue,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = project.githubUrl,
                fontSize = 14.sp,
                color = AppleBlue,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun TechTag(tech: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = ApplePurple.copy(alpha = 0.12f)
    ) {
        Text(
            text = tech,
            fontSize = 13.sp,
            color = ApplePurple,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

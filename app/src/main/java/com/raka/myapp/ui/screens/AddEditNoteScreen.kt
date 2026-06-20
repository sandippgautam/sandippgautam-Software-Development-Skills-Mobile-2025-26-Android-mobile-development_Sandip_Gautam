package com.raka.myapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.raka.myapp.data.Note
import com.raka.myapp.data.NoteCategory
import com.raka.myapp.ui.components.AppleButton
import com.raka.myapp.ui.theme.*
import com.raka.myapp.viewmodel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: NotesViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(NoteCategory.IDEAS) }
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppleBackground)
    ) {
        // Top Bar
        Surface(
            color = AppleCardBackground,
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = AppleBlue
                    )
                }
                
                Text(
                    text = "New Note",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppleTextPrimary
                )
                
                IconButton(
                    onClick = {
                        if (title.isNotBlank() && content.isNotBlank()) {
                            val note = Note(
                                title = title,
                                content = content,
                                category = selectedCategory
                            )
                            viewModel.addNote(note)
                            navController.popBackStack()
                        }
                    },
                    enabled = title.isNotBlank() && content.isNotBlank()
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Save",
                        tint = if (title.isNotBlank() && content.isNotBlank()) 
                            AppleBlue else AppleTextSecondary
                    )
                }
            }
        }
        
        // Form
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            // Title Input
            Text(
                text = "Title",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppleTextSecondary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text("Enter note title...", fontSize = 16.sp) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppleCardBackground,
                    unfocusedContainerColor = AppleCardBackground,
                    focusedIndicatorColor = AppleBlue,
                    unfocusedIndicatorColor = AppleDivider
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Category Selection
            Text(
                text = "Category",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppleTextSecondary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CategoryChip(
                    category = NoteCategory.WORK,
                    isSelected = selectedCategory == NoteCategory.WORK,
                    onClick = { selectedCategory = NoteCategory.WORK },
                    modifier = Modifier.weight(1f)
                )
                CategoryChip(
                    category = NoteCategory.STUDY,
                    isSelected = selectedCategory == NoteCategory.STUDY,
                    onClick = { selectedCategory = NoteCategory.STUDY },
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CategoryChip(
                    category = NoteCategory.IDEAS,
                    isSelected = selectedCategory == NoteCategory.IDEAS,
                    onClick = { selectedCategory = NoteCategory.IDEAS },
                    modifier = Modifier.weight(1f)
                )
                CategoryChip(
                    category = NoteCategory.CODE,
                    isSelected = selectedCategory == NoteCategory.CODE,
                    onClick = { selectedCategory = NoteCategory.CODE },
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Content Input
            Text(
                text = "Content",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppleTextSecondary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            TextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                placeholder = { Text("Write your note here...", fontSize = 15.sp) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppleCardBackground,
                    unfocusedContainerColor = AppleCardBackground,
                    focusedIndicatorColor = AppleBlue,
                    unfocusedIndicatorColor = AppleDivider
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp
                ),
                maxLines = 10
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Save Button
            AppleButton(
                text = "Save Note",
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        val note = Note(
                            title = title,
                            content = content,
                            category = selectedCategory
                        )
                        viewModel.addNote(note)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank() && content.isNotBlank()
            )
        }
    }
}

@Composable
fun CategoryChip(
    category: NoteCategory,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val categoryColor = when (category) {
        NoteCategory.WORK -> AppleBlue
        NoteCategory.STUDY -> AppleGreen
        NoteCategory.IDEAS -> AppleOrange
        NoteCategory.CODE -> ApplePurple
    }
    
    Surface(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) categoryColor else categoryColor.copy(alpha = 0.1f)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(
                text = category.displayName,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                color = if (isSelected) Color.White else categoryColor
            )
        }
    }
}

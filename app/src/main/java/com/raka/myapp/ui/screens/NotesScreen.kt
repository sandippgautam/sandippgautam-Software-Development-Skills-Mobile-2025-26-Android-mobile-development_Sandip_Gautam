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
import androidx.navigation.NavController
import com.raka.myapp.data.Note
import com.raka.myapp.data.NoteCategory
import com.raka.myapp.ui.components.AppleCard
import com.raka.myapp.ui.theme.*
import com.raka.myapp.viewmodel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredNotes = if (searchQuery.isEmpty()) {
        viewModel.notes
    } else {
        viewModel.notes.filter {
            it.title.contains(searchQuery, ignoreCase = true) ||
            it.content.contains(searchQuery, ignoreCase = true)
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppleBackground)
        ) {
            // Header with Search
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
                        text = "Smart Notes",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppleTextPrimary
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Search Bar
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        placeholder = { 
                            Text("Search notes...", color = AppleTextSecondary) 
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                tint = AppleTextSecondary
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(
                                        Icons.Default.Clear,
                                        contentDescription = "Clear",
                                        tint = AppleTextSecondary
                                    )
                                }
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = AppleBackground,
                            unfocusedContainerColor = AppleBackground,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                }
            }
            
            // Notes List
            if (filteredNotes.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Note,
                            contentDescription = null,
                            tint = AppleTextSecondary,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = if (searchQuery.isEmpty()) "No notes yet" else "No results found",
                            fontSize = 18.sp,
                            color = AppleTextSecondary
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredNotes, key = { it.id }) { note ->
                        NoteCard(
                            note = note,
                            onDelete = { viewModel.deleteNote(note) }
                        )
                    }
                }
            }
        }
        
        // Floating Action Button
        FloatingActionButton(
            onClick = { navController.navigate("add_edit_note/-1") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = AppleBlue,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Note",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    onDelete: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    val categoryColor = when (note.category) {
        NoteCategory.WORK -> AppleBlue
        NoteCategory.STUDY -> AppleGreen
        NoteCategory.IDEAS -> AppleOrange
        NoteCategory.CODE -> ApplePurple
    }
    
    AppleCard(
        backgroundColor = AppleCardBackground
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            // Category Badge
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = categoryColor.copy(alpha = 0.15f)
            ) {
                Text(
                    text = note.category.displayName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = categoryColor,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
            
            // Delete Button
            IconButton(
                onClick = { showDeleteDialog = true },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = AppleRed,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Title
        Text(
            text = note.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = AppleTextPrimary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Content
        Text(
            text = note.content,
            fontSize = 15.sp,
            color = AppleTextSecondary,
            lineHeight = 22.sp,
            maxLines = 3
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Timestamp
        Text(
            text = formatTimestamp(note.timestamp),
            fontSize = 13.sp,
            color = AppleTextSecondary
        )
    }
    
    // Delete Confirmation Dialog
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Note?", fontWeight = FontWeight.Bold) },
            text = { Text("This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete()
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = AppleRed
                    )
                ) {
                    Text("Delete", fontWeight = FontWeight.SemiBold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel", fontWeight = FontWeight.Medium)
                }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("MMM dd, yyyy • HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

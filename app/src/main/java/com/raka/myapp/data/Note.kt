package com.raka.myapp.data

data class Note(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val category: NoteCategory,
    val timestamp: Long = System.currentTimeMillis(),
    val color: String = category.color
)

enum class NoteCategory(val displayName: String, val color: String) {
    WORK("Work", "#007AFF"),
    STUDY("Study", "#34C759"),
    IDEAS("Ideas", "#FF9500"),
    CODE("Code", "#5856D6")
}

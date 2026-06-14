package com.raka.myapp.data

data class TimeEntry(
    val id: String = java.util.UUID.randomUUID().toString(),
    val activity: String,
    val duration: Long, // in milliseconds
    val startTime: Long = System.currentTimeMillis(),
    val isActive: Boolean = false
)

enum class ActivityType(val displayName: String) {
    CODING("Coding"),
    LEARNING("Learning"),
    MEETING("Meeting"),
    BREAK("Break"),
    RESEARCH("Research")
}

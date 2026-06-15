package com.raka.myapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.raka.myapp.data.ActivityType
import com.raka.myapp.data.TimeEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimeTrackerViewModel : ViewModel() {
    
    private val _entries = mutableStateListOf<TimeEntry>()
    val entries: List<TimeEntry> = _entries
    
    private val _currentActivity = MutableStateFlow<TimeEntry?>(null)
    val currentActivity: StateFlow<TimeEntry?> = _currentActivity
    
    var isTracking by mutableStateOf(false)
        private set
    
    private var startTime: Long = 0
    
    fun startTracking(activity: String) {
        if (!isTracking) {
            startTime = System.currentTimeMillis()
            isTracking = true
            _currentActivity.value = TimeEntry(
                activity = activity,
                duration = 0,
                startTime = startTime,
                isActive = true
            )
        }
    }
    
    fun stopTracking() {
        if (isTracking) {
            val endTime = System.currentTimeMillis()
            val duration = endTime - startTime
            
            _currentActivity.value?.let { current ->
                val completedEntry = current.copy(
                    duration = duration,
                    isActive = false
                )
                _entries.add(0, completedEntry)
            }
            
            isTracking = false
            _currentActivity.value = null
        }
    }
    
    fun getCurrentDuration(): Long {
        return if (isTracking) {
            System.currentTimeMillis() - startTime
        } else {
            0
        }
    }
    
    fun getTotalTimeByActivity(activity: String): Long {
        return _entries
            .filter { it.activity == activity }
            .sumOf { it.duration }
    }
    
    fun getTotalTime(): Long {
        return _entries.sumOf { it.duration }
    }
}

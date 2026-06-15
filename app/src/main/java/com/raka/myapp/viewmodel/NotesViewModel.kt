package com.raka.myapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.raka.myapp.data.Note
import com.raka.myapp.data.NoteCategory

class NotesViewModel : ViewModel() {
    
    private val _notes = mutableStateListOf<Note>()
    val notes: List<Note> = _notes
    
    private var _searchQuery = ""
    val searchQuery: String get() = _searchQuery
    
    init {
        // Add some sample notes
        _notes.addAll(
            listOf(
                Note(
                    title = "Welcome to DevPortfolio!",
                    content = "This is your smart notes section. Create notes, organize them by category, and search through them easily.",
                    category = NoteCategory.IDEAS
                ),
                Note(
                    title = "Kotlin Tips",
                    content = "Remember to use data classes for immutable data, sealed classes for restricted hierarchies, and coroutines for async operations.",
                    category = NoteCategory.CODE
                )
            )
        )
    }
    
    fun addNote(note: Note) {
        _notes.add(0, note)
    }
    
    fun deleteNote(note: Note) {
        _notes.remove(note)
    }
    
    fun updateNote(updatedNote: Note) {
        val index = _notes.indexOfFirst { it.id == updatedNote.id }
        if (index != -1) {
            _notes[index] = updatedNote
        }
    }
    
    fun updateSearchQuery(query: String) {
        _searchQuery = query
    }
    
    fun getFilteredNotes(): List<Note> {
        return if (_searchQuery.isEmpty()) {
            _notes
        } else {
            _notes.filter { 
                it.title.contains(_searchQuery, ignoreCase = true) ||
                it.content.contains(_searchQuery, ignoreCase = true)
            }
        }
    }
}

package dev.garousi.noteappkmm.android.note_list

import dev.garousi.noteappkmm.domain.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)
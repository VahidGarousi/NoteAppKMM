package dev.garousi.noteappkmm.android.note_list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.garousi.noteappkmm.data.time.DateTimeUtil
import dev.garousi.noteappkmm.domain.Note
import dev.garousi.noteappkmm.domain.NoteDataSource
import dev.garousi.noteappkmm.domain.use_cases.SearchNotes
import dev.garousi.noteappkmm.presentation.RedOrangeHex
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val TAG = "NoteListViewModel"

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val notesDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val searchNotes = SearchNotes()

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)


    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        NoteListState(
            notes = searchNotes.execute(notes = notes, query = searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = NoteListState(),
        started = SharingStarted.WhileSubscribed(5000)
    )


    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = notesDataSource.getAllNotes()
            Log.i(TAG, "loadNotes: ")
        }
    }


    fun onSearchTextChange(text: String) {
        savedStateHandle["searchText"] = text
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if (!isSearchActive.value) {
            savedStateHandle["searchText"] = ""
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            notesDataSource.deleteNoteById(id)
            loadNotes()
        }
    }

}
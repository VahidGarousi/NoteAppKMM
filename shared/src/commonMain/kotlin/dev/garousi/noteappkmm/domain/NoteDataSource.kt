package dev.garousi.noteappkmm.domain

interface NoteDataSource {
    suspend fun insertNote(note: Note)
    suspend fun getNoteById(id : Long) : Note?
    suspend fun getAllNotes() : List<Note>
    suspend fun deleteNoteById(id : Long)
}
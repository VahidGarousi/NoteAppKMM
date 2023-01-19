package dev.garousi.noteappkmm.data.note
import dev.garousi.noteappkmm.data.time.DateTimeUtil
import dev.garousi.noteappkmm.database.NoteDatabase
import dev.garousi.noteappkmm.domain.Note
import dev.garousi.noteappkmm.domain.NoteDataSource

class SQLDelightNoteDataSource constructor(
    private val db : NoteDatabase
) : NoteDataSource {
    private val queries = db.noteQueries
    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getNoteById(id: Long)  : Note? {
        return queries.getNoteById(id).executeAsOneOrNull()?.toNote()
    }


    override suspend fun getAllNotes(): List<Note> {
        return queries.getAllNotes().executeAsList().map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}
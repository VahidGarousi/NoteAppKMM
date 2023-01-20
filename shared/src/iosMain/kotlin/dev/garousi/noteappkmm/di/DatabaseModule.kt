package dev.garousi.noteappkmm.di

import dev.garousi.noteappkmm.data.local.DatabaseDriverFactory
import dev.garousi.noteappkmm.data.note.SQLDelightNoteDataSource
import dev.garousi.noteappkmm.database.NoteDatabase
import dev.garousi.noteappkmm.domain.NoteDataSource

class DatabaseModule {
    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource : NoteDataSource by lazy {
        SQLDelightNoteDataSource(NoteDatabase(factory.createDriver()))
    }
}
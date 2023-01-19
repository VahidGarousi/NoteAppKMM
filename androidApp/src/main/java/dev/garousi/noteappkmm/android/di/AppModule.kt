package dev.garousi.noteappkmm.android.di

import android.app.Application
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.garousi.noteappkmm.data.local.DatabaseDriverFactory
import dev.garousi.noteappkmm.data.note.SQLDelightNoteDataSource
import dev.garousi.noteappkmm.database.NoteDatabase
import dev.garousi.noteappkmm.domain.NoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SQLDelightNoteDataSource(NoteDatabase(driver))
    }
}
package com.nicholssoftware.jonsstudyapp.framework.di

import android.app.Application
import com.nicholssoftware.core.repository.NoteRepository
import com.nicholssoftware.jonsstudyapp.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideNoteRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}
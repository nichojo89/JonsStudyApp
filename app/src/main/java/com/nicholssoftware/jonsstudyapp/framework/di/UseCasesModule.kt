package com.nicholssoftware.jonsstudyapp.framework.di

import com.nicholssoftware.core.repository.NoteRepository
import com.nicholssoftware.core.usecase.AddNote
import com.nicholssoftware.core.usecase.GetAllNotes
import com.nicholssoftware.core.usecase.GetNote
import com.nicholssoftware.core.usecase.RemoveNote
import com.nicholssoftware.jonsstudyapp.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getUseCases(noteRepository: NoteRepository) =
        UseCases(
            AddNote(noteRepository),
            GetAllNotes(noteRepository),
            GetNote(noteRepository),
            RemoveNote(noteRepository),
        )
}
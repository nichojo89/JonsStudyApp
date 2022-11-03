package com.nicholssoftware.jonsstudyapp.framework

import android.content.Context
import com.nicholssoftware.core.data.Note
import com.nicholssoftware.core.repository.NoteDataSource
import com.nicholssoftware.jonsstudyapp.framework.db.DatabaseService
import com.nicholssoftware.jonsstudyapp.framework.db.NoteEntity

class RoomNoteDataSource(context: Context): NoteDataSource {
    val noteDao = DatabaseService.getInstance(context).noteDao()
    override suspend fun add(note: Note) =
        noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? =
        noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> =
        noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) =
        noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}
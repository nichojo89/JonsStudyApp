package com.nicholssoftware.jonsstudyapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholssoftware.core.data.Note
import com.nicholssoftware.jonsstudyapp.R
import com.nicholssoftware.jonsstudyapp.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotesListAdapter(var notes: ArrayList<Note>, val actions: ListAction) : RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ItemNoteBinding.bind(view)
        private val layout = binding.noteLayout
        private val noteTitle = binding.tvTitle
        private val noteContent = binding.tvContent
        private val noteDate = binding.tvDate
        private val noteWords = binding.wordCount

        fun bind(note: Note) {
            noteTitle.text = note.title
            noteContent.text = note.content

            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(note.updateTime)
            noteDate.text = "Last updated: ${sdf.format(resultDate)}"

            layout.setOnClickListener {actions.onClick(note.id)}
            noteWords.text = "Words: ${note.wordCount}"
        }
    }

    fun updateNotes(newNotes: List<Note>){
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder = NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int  = notes.size
}
package com.nicholssoftware.jonsstudyapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholssoftware.jonsstudyapp.R
import com.nicholssoftware.jonsstudyapp.databinding.FragmentNoteListBinding
import com.nicholssoftware.jonsstudyapp.framework.NoteListViewModel

class NoteListFragment : Fragment(), ListAction {
    private val notesListAdapter  = NotesListAdapter(arrayListOf(), this)
    private val viewModel: NoteListViewModel by lazy {
        ViewModelProvider(this).get(NoteListViewModel::class.java)
    }

    private lateinit var _binding: FragmentNoteListBinding
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.noteListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        binding.fabAddNote.setOnClickListener { goToNoteDetails() }

        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater,container, false)
        return _binding.root
    }

    private fun observeViewModel(){
        viewModel.notes.observe(viewLifecycleOwner, Observer { notesList ->
            binding.loadingBar.visibility = View.GONE
            binding.noteListView.visibility = View.VISIBLE
            notesListAdapter.updateNotes(notesList.sortedBy { it.updateTime })
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun goToNoteDetails(id: Long = 0L){
        val action = NoteListFragmentDirections.actionNoteListFragmentToNoteFragment(id)
        Navigation.findNavController(binding.noteListView).navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}
package com.nicholssoftware.jonsstudyapp.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nicholssoftware.core.data.Note
import com.nicholssoftware.jonsstudyapp.R
import com.nicholssoftware.jonsstudyapp.databinding.FragmentNoteBinding
import com.nicholssoftware.jonsstudyapp.databinding.FragmentNoteListBinding
import com.nicholssoftware.jonsstudyapp.framework.NoteViewModel


class NoteFragment : Fragment() {
    private var noteId = 0L
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    private var currentNote = Note("","",0L,0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
            if(noteId != 0L){
                viewModel.getNote(noteId)
            }
        }

        //fab goes back
        binding.fabCheck.setOnClickListener {
            if(binding.etTitle.text.toString() != ""
                || binding.etContent.text.toString() != ""){
                val time = System.currentTimeMillis()
                currentNote.title = binding.etTitle.text.toString()
                currentNote.content = binding.etContent.text.toString()
                currentNote.updateTime = time
                if(currentNote.id == 0L){
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if(it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(binding.etTitle).popBackStack()
            } else {
                Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                currentNote = it
                binding.etTitle.setText(it.title, TextView.BufferType.EDITABLE)
                binding.etContent.setText(it.content, TextView.BufferType.EDITABLE)
            }
        })
    }

    /**
     * TODO Convert to extention function
     */
    private fun hideKeyboard(){
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etTitle.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteNote -> {
                if(context != null && noteId != 0L){
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete note")
                        .setMessage("Are you sure?")

                        .setPositiveButton("Yes") {dialogInterface, i ->
                            viewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("Cancel"){dialogInterface, i -> }
                        .create()
                        .show()
                }
            }
        }
        return true
    }
}
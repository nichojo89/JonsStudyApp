package com.nicholssoftware.jonsstudyapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.Navigation
import com.nicholssoftware.jonsstudyapp.R
import com.nicholssoftware.jonsstudyapp.databinding.FragmentQuizBinding
import com.nicholssoftware.jonsstudyapp.framework.Constants

class QuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentQuizBinding.inflate(inflater,container,false)

        binding.btnStart.setOnClickListener{
            if(binding.etName.text!!.isEmpty()){
                Toast.makeText(context,"Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val action = QuizFragmentDirections.actionQuizFragmentToQuizQuestionsFragment(binding.etName.text.toString())
                Navigation.findNavController(binding.btnStart).navigate(action)
            }
        }
        return binding.root
    }
}
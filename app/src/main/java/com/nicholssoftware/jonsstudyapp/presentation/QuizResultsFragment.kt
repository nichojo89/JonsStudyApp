package com.nicholssoftware.jonsstudyapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nicholssoftware.jonsstudyapp.R
import com.nicholssoftware.jonsstudyapp.databinding.ActivityMainBinding
import com.nicholssoftware.jonsstudyapp.databinding.FragmentQuizResultsBinding


class QuizResultsFragment : Fragment() {
    private lateinit var _binding: FragmentQuizResultsBinding
    private val binding get() = _binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizResultsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding.tvName.text = QuizResultsFragmentArgs.fromBundle(it).userName
            val total = QuizResultsFragmentArgs.fromBundle(it).totalQuestions
            val correct = QuizResultsFragmentArgs.fromBundle(it).correctAnswers
            binding.tvScore.text = "Your score is $correct out of $total"
            binding.btnFinish.setOnClickListener{
                val action = QuizResultsFragmentDirections.actionQuizResultsFragmentToQuizFragment()
                //TODO
                Navigation.findNavController(binding.ivTrophy).navigate(action)
            }
        }
    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_result)
//        val username = intent.getStringExtra(Constants.USER_NAME)
//        findViewById<TextView>(R.id.tv_name).text = username
//
//        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
//        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
//        findViewById<TextView>(R.id.tv_score).text = "Your score is $correctAnswer out of $totalQuestions"
//        findViewById<Button>(R.id.btn_finish).setOnClickListener{
//            //TODO change to enter username activity
//            startActivity(Intent(this,MainActivity::class.java))
//        }
//    }
}
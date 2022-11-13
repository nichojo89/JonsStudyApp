package com.nicholssoftware.jonsstudyapp.presentation

import android.content.Intent
import android.content.Intent.getIntent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nicholssoftware.core.data.Question
import com.nicholssoftware.jonsstudyapp.R
import com.nicholssoftware.jonsstudyapp.databinding.FragmentQuizQuestionsBinding
import com.nicholssoftware.jonsstudyapp.framework.Constants


class QuizQuestionsFragment : Fragment(), View.OnClickListener {
    private var mPosition = 1
    private var mQuestionsList:ArrayList<Question>? = null
    private var mSelectedPosition = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    private lateinit var _binding: FragmentQuizQuestionsBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizQuestionsBinding.inflate(inflater,container,false)

        mQuestionsList = Constants.getQuestions()
        setQuestion()
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            mUserName = QuizQuestionsFragmentArgs.fromBundle(it).userName
        }
    }


    private fun setQuestion(){
        binding.svQuestionList.scrollTo(0,0)
        val question=mQuestionsList!![mPosition-1]
        defaultOptionsView()
        binding.btnSubmit.text =
            if(mPosition == mQuestionsList!!.size)
                "Finish" else "Submit"

        binding.progressBar.progress = mPosition
        binding.tvProgress.text = "$mPosition/${binding.progressBar.max}"

        binding.tvQuestion.text = question!!.question
        binding.ivImage.setImageResource(question!!.image)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView(){
        val o = ArrayList<TextView>()
        o.add(0,binding.tvOptionOne)
        o.add(1,binding.tvOptionTwo)
        o.add(2,binding.tvOptionThree)
        o.add(3,binding.tvOptionFour)

        for (option in o){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(binding.tvOptionOne,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(binding.tvOptionTwo,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(binding.tvOptionThree,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(binding.tvOptionFour,4)
            }
            R.id.btn_submit ->{
                if(mSelectedPosition==0){
                    mPosition++

                    //has user answers all questions?
                    when{
                        mPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        } else -> {
                            val action = QuizQuestionsFragmentDirections.actionQuizQuestionsFragmentToQuizResultsFragment(
                                    mUserName!!,
                                    mCorrectAnswers,
                                    mQuestionsList!!.size)

                            Navigation.findNavController(binding.tvQuestion).navigate(action)
                        }
                    }
                } else{
                    val question = mQuestionsList?.get(mPosition-1)
                    if(question!!.correctAnswer != mSelectedPosition){
                        answerView(mSelectedPosition,R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    binding.btnSubmit.text =
                        if(mPosition == mQuestionsList!!.size)
                            "Finish" else "Go to next question"
                    mSelectedPosition = 0
                }
            }
        }
    }
    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView,
                                   num:Int){
        defaultOptionsView()
        mSelectedPosition = num

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.selected_option_border
        )
    }
}
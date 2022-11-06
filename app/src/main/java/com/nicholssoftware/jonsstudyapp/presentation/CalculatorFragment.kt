package com.nicholssoftware.jonsstudyapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nicholssoftware.jonsstudyapp.databinding.FragmentCalculatorBinding
import com.nicholssoftware.jonsstudyapp.framework.CalculatorViewModel

class CalculatorFragment: Fragment() {
//TODO Get this working with XML to ViewModel bining
//class CalculatorFragment : FragmentX<FragmentCalculatorBinding>(R.layout.fragment_calculator) {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this).get(CalculatorViewModel::class.java)
    }

    /**
     * TODO Convert this to a handler
     */
    private val addDigit = View.OnClickListener {
        val btn = it as Button
        val newAnswer = "${viewModel.equation.value}${btn.text}"
        viewModel.equation.postValue(newAnswer)
    }

    /**
     * TODO Convert this to a handler
     */
    private val addDecimal = View.OnClickListener {
        if(!viewModel.equation.value!!.contains('.'))
            viewModel.equation.postValue("${viewModel.equation.value}.")
    }

    /**
     * TODO Convert this to a handler
     */
    private val backSpace = View.OnClickListener {
        val newValue = viewModel.equation.value!!.dropLast(1)
        viewModel.equation.postValue(newValue)
    }

    /**
     * TODO Convert this to a handler
     */
    private val allClear = View.OnClickListener {
        viewModel.equation.postValue("")
    }

    private val calculate = View.OnClickListener {
        val equation = viewModel.equation.value!!
        if(equation != ""){
            var firstTerm = ""
            var secondTerm = ""
            var operator : Char? = null
            for(i in equation.indices){
                if(equation[i].isDigit())
                    if(operator != null)
                        secondTerm += equation[i]
                    else
                        firstTerm += equation[i]
                else {
                    if(operator != null){
                        var ans = calc(firstTerm,secondTerm,operator)


                        viewModel.equation.postValue(ans.toString())
                        operator = null
                    }
                    operator = equation[i]
                }
            }
            val hhh = viewModel.equation.value!!
            if(!viewModel.equation.value!!.isEmpty()
                && firstTerm != ""
                && secondTerm != ""
                && operator != null){
                val a = calc(firstTerm,secondTerm,operator).toString()
                viewModel.answer.postValue(a)
            }
        }
    }
    private fun calc(firstTerm: String, secondTerm: String, operator: Char) : Int{
        return when(operator){
            '+' -> firstTerm.toInt() + secondTerm.toInt()
            '-' -> firstTerm.toInt() - secondTerm.toInt()
            '*' -> firstTerm.toInt() * secondTerm.toInt()
            '/' -> firstTerm.toInt() / secondTerm.toInt()
            '%' -> firstTerm.toInt() % secondTerm.toInt()
            else -> 0
        }
        //TODO  '(',')' -> firstTerm.toInt() - secondTerm.toInt()
    }

    private val operators = charArrayOf('+','-','*','/','%',')')

    private val addOperator = View.OnClickListener {
        if(viewModel.equation.value != ""){
            val l = viewModel.equation.value!!.last()
            if(!operators.contains(l)){
                val btn = it as Button
                val newAnswer = "${viewModel.equation.value}${btn.text}"
                viewModel.equation.postValue(newAnswer)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater,container,false)

        //TODO Use DataBinding in XML to Handlers instead of this
        binding.btnZero.setOnClickListener(addDigit)
        binding.btnOne.setOnClickListener(addDigit)
        binding.btnTwo.setOnClickListener(addDigit)
        binding.btnThree.setOnClickListener(addDigit)
        binding.btnFour.setOnClickListener(addDigit)
        binding.btnFive.setOnClickListener(addDigit)
        binding.btnSix.setOnClickListener(addDigit)
        binding.btnSeven.setOnClickListener(addDigit)
        binding.btnEight.setOnClickListener(addDigit)
        binding.btnNine.setOnClickListener(addDigit)

        binding.btnAdd.setOnClickListener(addOperator)
        binding.btnSubtract.setOnClickListener(addOperator)
        binding.btnMultiply.setOnClickListener(addOperator)
        binding.btnDivide.setOnClickListener(addOperator)
        binding.btnModulo.setOnClickListener(addOperator)
        binding.btnParanthesis.setOnClickListener(addOperator)

        binding.btnDecimal.setOnClickListener(addDecimal)
        binding.btnBackspace.setOnClickListener(backSpace)
        binding.btnAllClear.setOnClickListener(allClear)
        binding.btnCalculate.setOnClickListener(calculate)

        viewModel.equation.postValue("")

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel(){
        viewModel.equation.observe(viewLifecycleOwner, Observer { eq ->
            if(eq != null)
                binding.tvEquation.setText(eq)
        })
        viewModel.answer.observe(viewLifecycleOwner, Observer {ans ->
            if(ans != null)
                binding.tvResult.text = ans
        })
    }
    //Example of init logic
    //override fun FragmentCalculatorBinding.initialize() {}

    //@Bindable


    fun addDigit(view: View){

    }
}
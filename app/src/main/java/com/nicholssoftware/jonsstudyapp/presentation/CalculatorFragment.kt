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
        val newAnswer = "${viewModel.answer.value}${btn.text}"
        viewModel.answer.postValue(newAnswer)
    }

    /**
     * TODO Convert this to a handler
     */
    private val addDecimal = View.OnClickListener {
        if(!viewModel.answer.value!!.contains('.'))
            viewModel.answer.postValue("${viewModel.answer.value}.")
    }

    /**
     * TODO Convert this to a handler
     */
    private val backSpace = View.OnClickListener {
        val newValue = viewModel.answer.value!!.dropLast(1)
        viewModel.answer.postValue(newValue)
    }

    /**
     * TODO Convert this to a handler
     */
    private val allClear = View.OnClickListener {
        viewModel.answer.postValue("")
    }
    private val operators = charArrayOf('+','-','*','/','%',')')

    private val addOperator = View.OnClickListener {
        if(viewModel.answer.value != ""){
            val l = viewModel.answer.value!!.last()
            if(!operators.contains(l)){
                val btn = it as Button
                val newAnswer = "${viewModel.answer.value}${btn.text}"
                viewModel.answer.postValue(newAnswer)
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

        viewModel.answer.postValue("")

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel(){
        viewModel.answer.observe(viewLifecycleOwner, Observer { ans ->
            if(ans != null)
                binding.tvEquation.setText(ans)
        })
    }
    //Example of init logic
    //override fun FragmentCalculatorBinding.initialize() {}

    //@Bindable


    fun addDigit(view: View){

    }
}
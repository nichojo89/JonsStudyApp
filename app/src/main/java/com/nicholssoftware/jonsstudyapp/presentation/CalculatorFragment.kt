package com.nicholssoftware.jonsstudyapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.nicholssoftware.jonsstudyapp.R
import com.nicholssoftware.jonsstudyapp.databinding.FragmentCalculatorBinding
import com.nicholssoftware.jonsstudyapp.framework.FragmentX


class CalculatorFragment : FragmentX<FragmentCalculatorBinding>(R.layout.fragment_calculator) {

    //Example of init logic
    override fun FragmentCalculatorBinding.initialize() {}

    //@Bindable
    val answer = MutableLiveData<String>()

    fun addDigit(){
        val g = 0
        throw java.lang.Exception("test")
    }
}
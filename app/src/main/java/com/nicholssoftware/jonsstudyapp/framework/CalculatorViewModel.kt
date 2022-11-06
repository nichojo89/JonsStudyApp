package com.nicholssoftware.jonsstudyapp.framework

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CalculatorViewModel(application: Application): AndroidViewModel(application) {

    val answer = MutableLiveData<String>()

    fun addDigit(){
        val g = 0
        throw java.lang.Exception("test")
    }
}
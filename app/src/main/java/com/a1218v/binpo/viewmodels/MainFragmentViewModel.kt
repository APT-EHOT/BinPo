package com.a1218v.binpo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel : ViewModel() {

    val currentNumber: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        currentNumber.value = "0"
    }

    fun onKeyboardButtonClick(index: Int) {
        currentNumber.value = currentNumber.value?.let {
            it + when (index) {
                10 -> "C"
                11 -> "0"
                12 -> "OK"
                else -> (index).toString()
            }
        }
    }
}
package com.a1218v.binpo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel : ViewModel() {

    val counter: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    init {
        counter.value = 0
    }

    fun onBtnMainTestClick() {
        counter.value = counter.value?.let { it + 1 } // let checks for null and returns result from lambda
    }
}
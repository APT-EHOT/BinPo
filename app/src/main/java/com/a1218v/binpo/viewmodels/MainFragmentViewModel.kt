package com.a1218v.binpo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel : ViewModel() {

    val state: MutableLiveData<ResultState> = MutableLiveData<ResultState>(ResultState.BEGIN)
    val currentNumber: MutableLiveData<String> = MutableLiveData<String>("")

    private var numbersAmount: Int = 0

    private val targetNumber: Int = (1..100).random()
    var numberOfAttempts: Int = 1

    fun onKeyboardButtonClick(index: Int) {
        when (index) {
            10 -> eraseNumber()
            11 -> newNumber("0")
            12 -> submitNumber()
            else -> newNumber((index).toString())
        }
    }

    private fun newNumber(newNumber: String) {
        if (numbersAmount >= 3)
            return

        currentNumber.value = currentNumber.value?.let { it + newNumber }
        numbersAmount++
    }

    private fun eraseNumber() {
        currentNumber.value = currentNumber.value?.dropLast(1)
        numbersAmount--
    }

    private fun submitNumber() {
        val currentNumberInt = currentNumber.value?.toInt() ?: 0
        when {
            currentNumberInt > targetNumber -> state.value = ResultState.MORE
            currentNumberInt < targetNumber -> state.value = ResultState.LESS
            else -> state.value = ResultState.FINISH
        }
        currentNumber.value = ""
        numbersAmount = 0
        numberOfAttempts++
    }
}

enum class ResultState {
    BEGIN,
    LESS,
    MORE,
    FINISH
}
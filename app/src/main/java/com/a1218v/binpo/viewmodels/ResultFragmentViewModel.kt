package com.a1218v.binpo.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.a1218v.binpo.databinding.MainFragmentBinding
import com.a1218v.binpo.db.ScoreDao
import com.a1218v.binpo.db.ScoreDatabase
import com.a1218v.binpo.db.ScoreEntry
import kotlinx.coroutines.launch

class ResultFragmentViewModel @ViewModelInject constructor(
    private val scoreDao: ScoreDao
) : ViewModel() {

    var scores: MutableLiveData<List<ScoreEntry>> = MutableLiveData<List<ScoreEntry>>()

    fun onScreenStarted(newScore: Int, context: Context) {
        viewModelScope.launch {
            scoreDao.insertScore(ScoreEntry(0, "Player", newScore))
            scores.value = scoreDao.getBestScores()
        }

    }

}
package com.a1218v.binpo.viewmodels

import android.app.Application
import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.a1218v.binpo.databinding.MainFragmentBinding
import com.a1218v.binpo.db.ScoreDatabase
import com.a1218v.binpo.db.ScoreEntry
import kotlinx.coroutines.launch

class ResultFragmentViewModel : ViewModel() {

    var scores: MutableLiveData<List<ScoreEntry>> = MutableLiveData<List<ScoreEntry>>()

    fun onScreenStarted(newScore: Int, context: Context) {
        val database = Room.databaseBuilder(
            context,
            ScoreDatabase::class.java,
            "score_database"
        ).build()
        val scoreDao = database.scoreDao()

        viewModelScope.launch {
            scoreDao.insertScore(ScoreEntry(0,"Player", newScore))
            scores.value = scoreDao.getBestScores()
        }

    }

}
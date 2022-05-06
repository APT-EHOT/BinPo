package com.a1218v.binpo.db

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {

    @Query("SELECT * FROM score_table ORDER BY score DESC LIMIT 5")
    suspend fun getBestScores(): List<ScoreEntry>

    @Insert
    suspend fun insertScore(scoreEntry: ScoreEntry)
}
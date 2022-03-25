package com.a1218v.binpo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class ScoreEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val score: Int
)

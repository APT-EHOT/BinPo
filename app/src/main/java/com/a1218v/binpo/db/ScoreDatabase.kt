package com.a1218v.binpo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScoreEntry::class], version = 1)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun scoreDao(): ScoreDao

}
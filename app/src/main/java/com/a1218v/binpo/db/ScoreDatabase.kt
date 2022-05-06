package com.a1218v.binpo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [ScoreEntry::class], version = 1)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun scoreDao(): ScoreDao

    class Callback @Inject constructor(
        private val database: Provider<ScoreDatabase>
    ) : RoomDatabase.Callback()
}
package com.a1218v.binpo

import android.app.Application
import androidx.room.Room
import com.a1218v.binpo.db.ScoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        callback: ScoreDatabase.Callback
    ) = Room.databaseBuilder(
        app,
        ScoreDatabase::class.java,
        "score_database"
    )
        .addCallback(callback)
        .build()

    @Provides
    fun provideTaskDao(db: ScoreDatabase) = db.scoreDao()

}
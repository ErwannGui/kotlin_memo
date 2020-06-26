package com.ynov.memokotlin.di

import android.app.Application
import androidx.room.Room
import com.ynov.memokotlin.storage.MemoDao
import com.ynov.memokotlin.storage.MemoDatabase
import com.ynov.memokotlin.repository.MemoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


//Provide all the app level dependency here
@Module
class AppModule {


    // Method #1
    @Singleton
    @Provides
    fun providesAppDatabase(app:Application):MemoDatabase{
        return Room.databaseBuilder(app,MemoDatabase::class.java,"memo_database").build()
    }

    // Method #2
    @Singleton
    @Provides
    fun providesMemoDao(db: MemoDatabase): MemoDao {
        return db.memoDao()
    }

    // Method #3
    @Provides
    fun providesRepository(memoDao: MemoDao):MemoRepository{
        return MemoRepository(memoDao)
    }
}
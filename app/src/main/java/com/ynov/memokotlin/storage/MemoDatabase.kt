package com.ynov.memokotlin.storage

import androidx.room.Database
import androidx.room.RoomDatabase


// - Database Class
@Database(entities = [Memo::class],version = 1)
abstract class MemoDatabase: RoomDatabase() {
    abstract fun memoDao(): MemoDao
}
package com.ynov.memokotlin.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ynov.memokotlin.storage.MemoDao
import com.ynov.memokotlin.storage.Memo
import kotlinx.coroutines.*
import javax.inject.Inject

class MemoRepository @Inject constructor(val memoDao: MemoDao) {

    // Method #1
    //function to insert memo in database
    fun insert(memo: Memo) {
        CoroutineScope(Dispatchers.IO).launch {
            memoDao.insert(memo)
        }
    }

    // Method #2
    //function to delete memo in database
    fun delete(memo: Memo) {
        CoroutineScope(Dispatchers.IO).launch {
            memoDao.delete(memo)
        }
    }

    // Method #3
    //function to delete memo by Id in database
    fun deleteById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            memoDao.deleteById(id)
        }
    }

    // Method #4
    //function to update memo in database
    fun update(memo: Memo) {
        CoroutineScope(Dispatchers.IO).launch {
            memoDao.update(memo)
            Log.e("DEBUG", "update is called in repo")

        }
    }

    // Method #5
    //function to get all memos in database
    fun getAllMemos(): LiveData<List<Memo>>{
        return memoDao.getAllMemos()
    }
}
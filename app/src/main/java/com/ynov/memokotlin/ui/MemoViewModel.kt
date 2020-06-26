package com.ynov.memokotlin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ynov.memokotlin.storage.Memo
import com.ynov.memokotlin.repository.MemoRepository
import javax.inject.Inject

class MemoViewModel @Inject constructor(
    val memoRepository: MemoRepository
) : ViewModel() {


    //Database Operations in view model


    // Method #1
    fun insert(memo: Memo) {
        return memoRepository.insert(memo)
    }

    // Method #2
    fun delete(memo: Memo) {
        memoRepository.delete(memo)
    }

    // Method #3
    fun deleteById(id:Int){
        memoRepository.deleteById(id)
    }

    // Method #4
    fun update(memo: Memo) {
        Log.e("DEBUG","update is called in viewmodel")
        memoRepository.update(memo)
    }

    // Method #5
    fun getAllMemos(): LiveData<List<Memo>> {
        Log.e("DEBUG", "View model getallmemo")
        return memoRepository.getAllMemos()
    }


}
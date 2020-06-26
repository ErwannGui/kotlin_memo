package com.ynov.memokotlin.storage
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemoDao {

    // Method #1
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo: Memo): Long

    // Method #2
    @Update
    fun update(memo: Memo)

    // Method #3
    @Query("delete from tbl_memo where id = :id")
    fun deleteById(id: Int)

    // Method #4
    @Delete
    fun delete(memo: Memo)

    // Method #5
    @Query("select * from tbl_memo")
    fun getAllMemos():LiveData<List<Memo>>
}
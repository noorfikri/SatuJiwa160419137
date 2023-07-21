package com.example.satujiwa160419137.model

import androidx.room.*

@Dao
interface HistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHistory(vararg history: History)

    @Query("SELECT * FROM history ORDER BY id DESC")
    fun selectAllHistory(): List<History>

    @Query("SELECT * FROM history WHERE id= :id")
    fun selectHistory(id:Int): History

    @Delete
    fun deleteHistory(history: History)

    @Query("UPDATE history SET title=:title, detail=:detail, curval=:curval, goalval=:goalval, img=:img, creator=:accountId WHERE id=:id")
    fun updateHistory(title:String,detail:String, curval:Int, goalval:Int, img:String, accountId:Int, id:Int)
}

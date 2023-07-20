package com.example.satujiwa160419137.model

import androidx.room.*

@Dao
interface HistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHistory(vararg history: History)

    @Query("SELECT * FROM history")
    fun selectAllHistory(): List<History>

    @Query("SELECT * FROM history WHERE id= :id")
    fun selectHistory(id: Int): History

    @Delete
    fun deleteHistory(history: History)
}

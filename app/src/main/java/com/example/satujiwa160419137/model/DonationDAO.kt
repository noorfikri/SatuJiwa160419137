package com.example.satujiwa160419137.model

import androidx.room.*

@Dao
interface DonationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDonation(vararg donasi: Donasi)

    @Query("SELECT * FROM donasi ORDER BY id DESC")
    fun selectAllDonation(): List<Donasi>

    @Query("SELECT * FROM donasi WHERE id= :id")
    fun selectDonation(id:Int): Donasi

    @Delete
    fun deleteDonation(donasi: Donasi)

    @Query("UPDATE donasi SET title=:title, detail=:detail, curval=:curval, goalval=:goalval, img=:img, creator=:accountId WHERE id=:id")
    fun updateDonation(title:String,detail:String, curval:Int, goalval:Int, img:String, accountId:Int, id:Int)
}
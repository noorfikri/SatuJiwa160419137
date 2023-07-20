package com.example.satujiwa160419137.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccountDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAccount(vararg account: Account)

    @Query("SELECT * FROM account")
    fun selectAllAccount(): List<Account>

    @Query("SELECT * FROM account WHERE id=:id")
    fun selectAccount(id:Int): Account

    @Query("SELECT * FROM account WHERE username=:username, password=:password")
    fun checkAccount(username: String, password: String):Account

    @Delete
    fun deleteAccount(account: Account)

    @Query("UPDATE account SET username=:username, password=:password, img=:img WHERE id =:id")
    fun updateAccount(username:String, password:String, img:String, id:Int)
}
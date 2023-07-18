package com.example.satujiwa160419137.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @ColumnInfo(name="username")
    var username:String?,
    @ColumnInfo(name="password")
    var password:String?="",
    @ColumnInfo(name="img")
    var imgUrl:String?,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

//data class Donate(
//    val id:Int?,
//    val title:String?,
//    val curVal:Int?,
//    val goalVal:Int?,
//    val detail:String?,
//    val img:String?,
//    val creator:Account?,
//)
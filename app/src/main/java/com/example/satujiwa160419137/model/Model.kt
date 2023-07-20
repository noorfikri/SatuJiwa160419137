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
@Entity
data class Donasi(
    @PrimaryKey(autoGenerate = true)
   val id:Int=0,
    @ColumnInfo(name="title")
   val title:String?,
    @ColumnInfo(name = "curval")
   val curVal:Int?,
    @ColumnInfo(name="goalval")
   val goalVal:Int?,
    @ColumnInfo(name="detail")
   val detail:String?,
    @ColumnInfo(name="img")
   val img:String?,
    @ColumnInfo(name="creator")
   val creator:Int?,
)

@Entity
data class History(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name="donasi")
    val donasi:Int?
    ,)
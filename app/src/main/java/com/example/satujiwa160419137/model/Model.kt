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
    var imgUrl:String?="",
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
@Entity
data class Donasi(
    @ColumnInfo(name="title")
   var title:String?,
    @ColumnInfo(name = "curval")
   var curVal:Int?,
    @ColumnInfo(name="goalval")
   var goalVal:Int?,
    @ColumnInfo(name="detail")
   var detail:String?,
    @ColumnInfo(name="img")
   var img:String?,
    @ColumnInfo(name="creator")
   var creator:Int?,

    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
)

@Entity
data class History(
    @ColumnInfo(name="title")
    var title:String?,
    @ColumnInfo(name = "curval")
    var curVal:Int?,
    @ColumnInfo(name="goalval")
    var goalVal:Int?,
    @ColumnInfo(name="detail")
    var detail:String?,
    @ColumnInfo(name="img")
    var img:String?,
    @ColumnInfo(name="creator")
    var creator:Int?,

    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
)
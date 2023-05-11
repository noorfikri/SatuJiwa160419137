package com.example.satujiwa160419137.model

data class Account(
    val id:Int?,
    val username:String?,
    val password:String?="",
    val imgUrl:String?,
)

data class Donate(
    val id:Int?,
    val title:String?,
    val curVal:Int?,
    val goalVal:Int?,
    val detail:String?,
    val img:String?,
    val creator:Account?,
)
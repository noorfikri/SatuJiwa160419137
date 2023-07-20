package com.example.satujiwa160419137.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.satujiwa160419137.util.ACCOUNTMIGRATION_1_1
import com.example.satujiwa160419137.util.HISTORYNMIGRATION_1_1

@Database(entities = arrayOf(History::class), version = 1)
abstract class HistoryDatabase: RoomDatabase(){
    abstract fun HistoryDAO(): HistoryDAO

    companion object{
        @Volatile private var instance: HistoryDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HistoryDatabase::class.java,
            "historydb").addMigrations(HISTORYNMIGRATION_1_1).build()

        operator fun invoke(context: Context){
            if (instance!=null){
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}
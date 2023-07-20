package com.example.satujiwa160419137.util

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ImageView
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.model.AccountDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
            }
            override fun onError(e: Exception?) {
            }
        })
}

fun buildDatabase(context: Context):AccountDatabase{
    val db = Room.databaseBuilder(
        context, AccountDatabase::class.java, "satujiwa"
    ).addMigrations(ACCOUNTMIGRATION_1_1, DONATIONMIGRATION_1_1, HISTORYNMIGRATION_1_1).build()

    return db
}


val ACCOUNTMIGRATION_1_1 =object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE account (id INTEGER PRIMARY KEY NOT NULL, username TEXT, password TEXT," +
                    "img TEXT)"
        )

    }
}

val DONATIONMIGRATION_1_1 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE donasi (id INTEGER PRIMARY KEY NOT NULL, title TEXT, curval INTEGER, goalval INTEGER, detail TEXT," +
                    "img TEXT, creator INTEGER)"
        )

    }
}

val HISTORYNMIGRATION_1_1 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE history (id INTEGER PRIMARY KEY NOT NULL, donasi INTEGER)"
        )

    }
}
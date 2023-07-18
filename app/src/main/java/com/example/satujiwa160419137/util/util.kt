package com.example.satujiwa160419137.util

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ImageView
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.model.ModelDatabase
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

fun buildDatabase(context: Context):ModelDatabase{
    val db = Room.databaseBuilder(
        context, ModelDatabase::class.java, "satujiwa"
    ).addMigrations(MIGRATION_1_2).build()

    return db
}


val MIGRATION_1_2 =object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE Account (id INTEGER PRIMARY KEY NOT NULL, username TEXT, password TEXT," +
                    "img TEXT)"
        )

    }
}
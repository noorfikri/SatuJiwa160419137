package com.example.satujiwa160419137.util

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.model.AccountDatabase
import com.example.satujiwa160419137.model.DonationDatabase
import com.example.satujiwa160419137.model.HistoryDatabase
import com.google.android.material.textfield.TextInputEditText
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

@BindingAdapter("android:imageUrl")
fun loadPhotoUrl(view: ImageView, url: String?){
    view.loadImage(url)
}

@BindingAdapter("android:text")
fun setText(view: TextInputEditText, num:Int){
    if(view.text != null && view.text.toString() != ""){
        view.setText(num.toString())
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getText(view: TextInputEditText): Int {
    if(view.text != null && view.text.toString() != ""){
        return view.text.toString().toInt()
    }else{
        return 0
    }
}

fun buildAccountDatabase(context: Context):AccountDatabase{
    val db = Room.databaseBuilder(
        context, AccountDatabase::class.java, "satujiwaaccount"
    ).addMigrations(ACCOUNTMIGRATION_1_1).build()

    return db
}

fun buildDonationDatabase(context: Context):DonationDatabase{
    val db = Room.databaseBuilder(
        context, DonationDatabase::class.java, "satujiwadonation"
    ).addMigrations(DONATIONMIGRATION_1_1).build()

    return db
}



fun buildHistorytDatabase(context: Context):HistoryDatabase{
    val db = Room.databaseBuilder(
        context, HistoryDatabase::class.java, "satujiwahistor"
    ).addMigrations(HISTORYNMIGRATION_1_1).build()

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
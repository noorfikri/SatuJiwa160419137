package com.example.satujiwa160419137.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.model.Donasi
import com.example.satujiwa160419137.util.buildAccountDatabase
import com.example.satujiwa160419137.util.buildDonationDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DonationDetailViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val donationLD = MutableLiveData<Donasi>()
    val donationAccountLD = MutableLiveData<Account>()
    val TAG = "volleyDonationDetailTag"
    private var queue:RequestQueue? = null
    private val job = Job()

    fun get(donateId:Int){
        launch {
            val db = buildDonationDatabase(getApplication())
            donationLD.postValue(db.donationDAO().selectDonation(donateId))
        }

    }

    fun makeDonation(title:String, detail:String, curval:Int, goalval:Int, img:String, creator:Int,donateId: Int){
        launch {
            val db = buildDonationDatabase(getApplication())
            db.donationDAO().updateDonation(title,detail,curval,goalval,img,creator,donateId)

        }
    }

    fun getCreator(id:Int){
        launch {
            val db = buildAccountDatabase(getApplication())
            donationAccountLD.postValue(db.AccountDAO().selectAccount(id))
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}
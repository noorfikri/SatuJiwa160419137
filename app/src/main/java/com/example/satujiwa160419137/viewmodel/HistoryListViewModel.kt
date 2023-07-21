package com.example.satujiwa160419137.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.satujiwa160419137.model.Donasi
import com.example.satujiwa160419137.model.History
import com.example.satujiwa160419137.util.buildDonationDatabase
import com.example.satujiwa160419137.util.buildHistorytDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HistoryListViewModel(application: Application):AndroidViewModel(application),CoroutineScope {
    val donateLD = MutableLiveData<ArrayList<History>>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyDonateListTag"
    private var queue:RequestQueue? = null
    private val job = Job()

    fun getHistory() {
        loadingLD.value = true

        launch {
            val db = buildHistorytDatabase( getApplication())
            donateLD.postValue(db.HistoryDAO().selectAllHistory() as ArrayList<History>?)
            loadingLD.postValue(false)
        }

        /*queue = Volley.newRequestQueue(getApplication())
        val url = "https://api.npoint.io/8f8d91787ff7067eba58"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Donasi>>(){}.type
                val result = Gson().fromJson<ArrayList<Donasi>>(it, sType)
                donateLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)*/
    }

    fun createHistory(history: History){
        launch {
            val db = buildHistorytDatabase(getApplication())
            db.HistoryDAO().insertAllHistory(history)

        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}
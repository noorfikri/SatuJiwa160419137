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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DonationListViewModel(application: Application):AndroidViewModel(application) {
    val donateLD = MutableLiveData<ArrayList<Donasi>>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyDonateListTag"
    private var queue:RequestQueue? = null

    fun getDonation() {
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
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
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
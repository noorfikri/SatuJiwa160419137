package com.example.satujiwa160419137.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.satujiwa160419137.model.Donate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DonationDetailViewModel(application: Application):AndroidViewModel(application) {
    val donationLD = MutableLiveData<Donate>()
    val TAG = "volleyDonationDetailTag"
    private var queue:RequestQueue? = null

    fun get(donateId:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://api.npoint.io/8f8d91787ff7067eba58/"+donateId

        val stringRequest = StringRequest(
            Request.Method.GET,url,{
                val sType = object : TypeToken<Donate>(){}.type
                val result = Gson().fromJson<Donate>(it,sType)

                donationLD.value = result
                Log.d("detailvolley",result.toString())
            },{
                Log.d("detailvolley",it.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AccountListViewModel(application: Application):AndroidViewModel(application) {
    val accountsLD = MutableLiveData<ArrayList<Account>>()
    val accountLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTAG"
    private var queue:RequestQueue? = null

    val newAccountTemp = ArrayList<Account>()

    fun fetchAccounts(){
        accountLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://api.npoint.io/162b034419f471720338"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Account>>(){}.type
                val result = Gson().fromJson<ArrayList<Account>>(it, sType)
                accountsLD.value = result
                loadingLD.value = false

                if (newAccountTemp.isNotEmpty()){
                    accountsLD.value?.addAll(newAccountTemp)
                }

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                accountLoadErrorLD.value = true
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun addAccount(newAccount : Account){
        newAccountTemp.add(newAccount)
        accountsLD.value?.add(newAccount)
    }

    override fun onCleared() {
        super.onCleared()

        queue?.cancelAll(TAG)
    }
}
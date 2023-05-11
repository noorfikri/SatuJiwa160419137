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

class AccountDetailViewModel(application: Application):AndroidViewModel(application) {
    val accountLD = MutableLiveData<Account>()
    val TAG= "volleyAccountDetailTAG"
    private var queue:RequestQueue? = null

    fun get(accountId:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://api.npoint.io/162b034419f471720338/"+accountId

        val stringRequest = StringRequest(
            Request.Method.GET,url,{
                val sType = object : TypeToken<Account>(){}.type
                val result = Gson().fromJson<Account>(it,sType)

                accountLD.value = result
                Log.d("detailaccountvolley",result.toString())
            },{
                Log.d("detailaccountvolley",it.toString())
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
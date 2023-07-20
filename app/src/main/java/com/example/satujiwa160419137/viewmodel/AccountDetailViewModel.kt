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
import com.example.satujiwa160419137.util.buildDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AccountDetailViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val accountLD = MutableLiveData<Account>()
    val TAG= "volleyAccountDetailTAG"
    private var queue:RequestQueue? = null

    private var job = Job()

    fun get(accountId:String){
        launch {
            val db = buildDatabase(getApplication())
            accountLD.value = db.AccountDAO().selectAccount(accountId.toInt())
        }
    }

    fun update(username:String, password:String, imgUrl:String, id:Int){
        launch {
            val db = buildDatabase(getApplication())
            db.AccountDAO().updateAccount(username, password,imgUrl, id)
        }
    }
//    fun get(accountId:String){
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://api.npoint.io/162b034419f471720338/"+accountId
//
//        val stringRequest = StringRequest(
//            Request.Method.GET,url,{
//                val sType = object : TypeToken<Account>(){}.type
//                val result = Gson().fromJson<Account>(it,sType)
//
//                accountLD.value = result
//                Log.d("detailaccountvolley",result.toString())
//            },{
//                Log.d("detailaccountvolley",it.toString())
//            }
//        )
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//
//    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}
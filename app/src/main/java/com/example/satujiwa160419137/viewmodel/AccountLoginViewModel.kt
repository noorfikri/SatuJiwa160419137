package com.example.satujiwa160419137.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.util.buildAccountDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AccountLoginViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val loginAccountLD = MutableLiveData<Account>()
    val isLogedInLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun registerUser(user: Account){
        launch {
            val db = buildAccountDatabase(getApplication())

           db.AccountDAO().insertAllAccount(user)
        }
//        Log.d("checklogin","checking login")
//        if(loginAccountLD.value == null){
//            isLogedInLD.value = false
//            Log.d("checklogin","account not loged in")
//        }else if (loginAccountLD.value != null){
//            isLogedInLD.value = true
//            Log.d("checklogin","account logged")
//        }
    }

    fun checkLogin(username:String, password:String){
        launch {
            val db = buildAccountDatabase(getApplication())

            loginAccountLD.postValue(db.AccountDAO().checkAccount(username,password))
        }
//        Log.d("checklogin","checking login")
//        if(loginAccountLD.value == null){
//            isLogedInLD.value = false
//            Log.d("checklogin","account not loged in")
//        }else if (loginAccountLD.value != null){
//            isLogedInLD.value = true
//            Log.d("checklogin","account logged")
//        }
    }

    fun login(account:Account){
        Log.d("login","login in")
        if(loginAccountLD.value==null){
            loginAccountLD.value = account
            isLogedInLD.value = true
            Log.d("login","logged in")
            Log.d("login",account.toString())
        }
    }

    fun logout(){
        Log.d("logout","loging out")
        if(loginAccountLD.value!=null){
            isLogedInLD.value = false
            Log.d("logout","logged out")
        }
    }
}
package com.example.satujiwa160419137.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.util.buildDatabase
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
        get() = job + Dispatchers.Main

    fun registerUser(user: List<Account>){
        launch {
            val db = buildDatabase(getApplication())

           db.AccountDAO().insertAllAccount(*user.toTypedArray())
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
            val db = buildDatabase(getApplication())

            loginAccountLD.value = db.AccountDAO().checkAccount(username,password)
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
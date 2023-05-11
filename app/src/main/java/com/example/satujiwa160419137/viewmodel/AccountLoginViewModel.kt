package com.example.satujiwa160419137.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.satujiwa160419137.model.Account

class AccountLoginViewModel(application: Application):AndroidViewModel(application) {
    val loginAccountLD = MutableLiveData<Account>()
    val isLogedInLD = MutableLiveData<Boolean>()

    fun checkLogin(){

        Log.d("checklogin","checking login")
        if(loginAccountLD.value == null){
            isLogedInLD.value = false
            Log.d("checklogin","account not loged in")
        }else if (loginAccountLD.value != null){
            isLogedInLD.value = true
            Log.d("checklogin","account logged")
        }
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
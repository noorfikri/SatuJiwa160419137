package com.example.satujiwa160419137.util

import android.view.View
import com.example.satujiwa160419137.model.Account

interface RegisterButtonListener{
    fun onRegisterButton(v:View)
}

interface LoginButtonListener{
    fun onLoginButton(v:View)
}

interface EditProfileButtonListener{
    fun onEditProfileButton(v: View,user:Account)
}

interface DeleteAccountButtonListener{
    fun onDeleteAccountButton(v:View, account: Account)
}

interface LogoutButtonListener{
    fun onLogoutButton(v:View)
}
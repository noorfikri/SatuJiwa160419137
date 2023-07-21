package com.example.satujiwa160419137.util

import android.view.View
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.model.Donasi

interface RegisterButtonListener{
    fun onRegisterButton(v:View)
}

interface FABCreateDonationListener{
    fun onFABCreateDonation(v: View)
}

interface CreateDonationListener{
    fun onCreateDonation(v: View)
}

interface DetailDonationListener{
    fun onDetailDonation(v:View, donasi: Donasi)
}

interface MakeDonationListener{
    fun onMakeDonation(v:View, donasi: Donasi)
}

interface ValueDonationChangeListener{
    fun onValueDonationChange(v:View,donateValue:Int)
}

interface DonationBackListener{
    fun onDonationBack(v:View, donation:Donasi, value:Int)
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
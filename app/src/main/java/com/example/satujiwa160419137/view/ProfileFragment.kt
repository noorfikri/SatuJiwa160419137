package com.example.satujiwa160419137.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.util.loadImage
import com.example.satujiwa160419137.viewmodel.AccountDetailViewModel
import com.example.satujiwa160419137.viewmodel.AccountLoginViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private lateinit var accountDetailViewModel: AccountDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedFile = "com.example.satujiwa160419137"
        val sharedPref = activity!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var loggedID = ""

        if(sharedPref.getString("LOGGED_ID","")!=""){
            loggedID = sharedPref.getString("LOGGED_ID","").toString()
        }

        val btnEditProfile = view.findViewById<Button>(R.id.btnToEditProfile)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout2)

        accountDetailViewModel = ViewModelProvider(this).get(AccountDetailViewModel::class.java)
        accountDetailViewModel.get(loggedID)

        observeAccountViewModel(view)

        btnLogout.setOnClickListener {
            doLogout(view)
        }
    }

    fun observeAccountViewModel(view: View){
        accountDetailViewModel.accountLD.observe(viewLifecycleOwner,{
            val txtUsername = view.findViewById<TextView>(R.id.txtProfileUsername)
            val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)

            txtUsername.text = it.username.toString()
            imgProfile.loadImage(it.imgUrl)
        })
    }

    fun doLogout(view: View){
        val sharedFile = "com.example.satujiwa160419137"
        val sharedPref = activity!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        sharedPref.edit().putString("LOGGED_ID","").apply()

        val action = ProfileFragmentDirections.actionLogoutProfile()
        Navigation.findNavController(view).navigate(action)
    }

}
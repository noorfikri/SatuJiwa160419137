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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.databinding.FragmentLoginBinding
import com.example.satujiwa160419137.databinding.FragmentProfileBinding
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.util.EditProfileButtonListener
import com.example.satujiwa160419137.util.LogoutButtonListener
import com.example.satujiwa160419137.util.loadImage
import com.example.satujiwa160419137.viewmodel.AccountDetailViewModel
import com.example.satujiwa160419137.viewmodel.AccountLoginViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment(), LogoutButtonListener, EditProfileButtonListener {
    private lateinit var accountDetailViewModel: AccountDetailViewModel
    var loggedID = ""

    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val btnEditProfile = view.findViewById<Button>(R.id.btnToEditProfile)
//        val txtUsername = view.findViewById<TextView>(R.id.txtProfileUsername)
//        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)

        var usernameEditTemp =""
        var linkEditTemp =""

        val sharedFile = "com.example.satujiwa160419137"
        val sharedPref = activity!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        if(sharedPref.getString("LOGGED_ID","")!=""){
            loggedID = sharedPref.getString("LOGGED_ID","").toString()
        }

        dataBinding.editProfileListener = this
        dataBinding.logoutListener = this
//        val btnLogout = view.findViewById<Button>(R.id.btnLogout2)

        accountDetailViewModel = ViewModelProvider(this).get(AccountDetailViewModel::class.java)
        accountDetailViewModel.get(loggedID)

        observeAccountViewModel(view)
//        if (arguments != null){
//
//            usernameEditTemp = ProfileFragmentArgs.fromBundle(requireArguments()).tempEditUsernameProfile.toString()
//            linkEditTemp = ProfileFragmentArgs.fromBundle(requireArguments()).tempEditLinkProfile.toString()
//
//            if(usernameEditTemp.equals("_")|| linkEditTemp.equals("_")){
//                observeAccountViewModel(view)
//            }else{
//                Log.d("usernameedit",usernameEditTemp.toString())
//                Log.d("linkedit",linkEditTemp.toString())
//                txtUsername.text = usernameEditTemp
//                imgProfile.loadImage(linkEditTemp)
//            }
//
//        }

//        btnLogout.setOnClickListener {
//            doLogout(view)
//        }

//        btnEditProfile.setOnClickListener {
//            val action = ProfileFragmentDirections.actionEditProfile(usernameEditTemp.toString(), linkEditTemp.toString(), loggedID)
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    fun observeAccountViewModel(view: View){
        accountDetailViewModel.accountLD.observe(viewLifecycleOwner,{
            dataBinding.user = it
        })
    }
//    fun observeAccountViewModel(view: View){
//        val txtUsername = view.findViewById<TextView>(R.id.txtProfileUsername)
//        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
//        var usernameEditTemp = ""
//        var linkEditTemp = ""
//
//            accountDetailViewModel.accountLD.observe(viewLifecycleOwner,{
//
//                txtUsername.text = it.username.toString()
//                imgProfile.loadImage(it.imgUrl)
//
//                usernameEditTemp = it.username.toString()
//                linkEditTemp = it.imgUrl.toString()
//            })
//
//    }

    fun doLogout(view: View){
        val sharedFile = "com.example.satujiwa160419137"
        val sharedPref = activity!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        sharedPref.edit().putString("LOGGED_ID","").apply()

        val action = ProfileFragmentDirections.actionLogoutProfile()
        Navigation.findNavController(view).navigate(action)
    }

    override fun onEditProfileButton(v: View, obj: Account) {
        val usernameEditTemp = obj.username
        val linkEditTemp = obj.imgUrl

        val action = ProfileFragmentDirections.actionEditProfile(usernameEditTemp.toString(), linkEditTemp.toString(), loggedID)
        Navigation.findNavController(v).navigate(action)
    }

    override fun onLogoutButton(v: View) {
        doLogout(v)
    }

}
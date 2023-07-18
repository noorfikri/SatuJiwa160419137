package com.example.satujiwa160419137.view

import android.os.Bundle
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
import com.example.satujiwa160419137.databinding.FragmentProfileBinding
import com.example.satujiwa160419137.databinding.FragmentProfileEditBinding
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.util.loadImage
import com.example.satujiwa160419137.viewmodel.AccountDetailViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [ProfileEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileEditFragment : Fragment(), EditProfileButtonListener {
    private lateinit var accountDetailViewModel: AccountDetailViewModel
    private lateinit var dataBinding: FragmentProfileEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileEditBinding>(inflater,R.layout.fragment_profile_edit, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var btnEdit = view.findViewById<Button>(R.id.btnEditProfile)
//        val txtEditUsername = view.findViewById<TextView>(R.id.txtUsernameProfileEdit)
//        val txtEditLink = view.findViewById<TextView>(R.id.txtImgUrlEdit)
        var accountId = ""
        var editUsername = ""
        var editLink = ""

        if(arguments != null){
            accountId = ProfileEditFragmentArgs.fromBundle(requireArguments()).userID.toString()
            editUsername = ProfileEditFragmentArgs.fromBundle(requireArguments()).tempEditUsernameEdit.toString()
            editLink = ProfileEditFragmentArgs.fromBundle(requireArguments()).tempEditLinkEdit.toString()
        }

        dataBinding.editProfileListener = this

        accountDetailViewModel = ViewModelProvider(this).get(AccountDetailViewModel::class.java)
        accountDetailViewModel.get(accountId)

        observeAccountDetailViewModel(view)

//        btnEdit.setOnClickListener {
//            if (txtEditLink.text != ""){
//                editLink = txtEditLink.text.toString()
//            }
//
//            if (txtEditUsername.text != ""){
//                editUsername = txtEditUsername.text.toString()
//            }
//
//            val action = ProfileEditFragmentDirections.actionProfileEditBack(editUsername,editLink)
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    fun observeAccountDetailViewModel(view: View){
        accountDetailViewModel.accountLD.observe(viewLifecycleOwner,{
//            val txtUsername = view.findViewById<TextView>(R.id.txtUsernameProfileEdit)
//            val txtlink = view.findViewById<TextView>(R.id.txtImgUrlEdit)
//            val imgView = view.findViewById<ImageView>(R.id.imgProfileEdit)
                dataBinding.user = it
//            txtUsername.text = it.username.toString()
//            txtlink.text = it.imgUrl.toString()
//            imgView.loadImage(it.imgUrl)
        })
    }

    override fun onEditProfileButton(v: View, obj: Account) {
        accountDetailViewModel.update(obj.username!!, obj.password!!, obj.imgUrl!!, obj.id)

        val action = ProfileEditFragmentDirections.actionProfileEditBack(obj.username.toString(),obj.imgUrl.toString())
        Navigation.findNavController(v).navigate(action)
    }
}
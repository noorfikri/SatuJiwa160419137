package com.example.satujiwa160419137.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.util.loadImage
import com.example.satujiwa160419137.viewmodel.AccountDetailViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileDetailFragment : Fragment() {
    private lateinit var accountDetailViewModel: AccountDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var accountId = ""

        if(arguments != null){
            accountId = ProfileDetailFragmentArgs.fromBundle(requireArguments()).accountId.toString()
        }

        accountDetailViewModel = ViewModelProvider(this).get(AccountDetailViewModel::class.java)
        accountDetailViewModel.get(accountId)

        observeAccountDetailViewModel(view)
    }

    fun observeAccountDetailViewModel(view: View){
        accountDetailViewModel.accountLD.observe(viewLifecycleOwner,{
            val txtUsername = view.findViewById<TextView>(R.id.txtProfileDetailUsername)
            val imgView = view.findViewById<ImageView>(R.id.imgProfileDetail)

            txtUsername.text = it.username.toString()
            imgView.loadImage(it.imgUrl)
        })
    }
}
package com.example.satujiwa160419137.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.databinding.FragmentCreateDonationBinding
import com.example.satujiwa160419137.databinding.FragmentRegisterBinding
import com.example.satujiwa160419137.model.Donasi
import com.example.satujiwa160419137.util.CreateDonationListener
import com.example.satujiwa160419137.viewmodel.DonationListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateDonationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateDonationFragment : Fragment(), CreateDonationListener {
    private lateinit var donationViewModel:DonationListViewModel
    private lateinit var dataBinding:FragmentCreateDonationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_donation, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        donationViewModel = ViewModelProvider(this).get(DonationListViewModel::class.java)

        dataBinding.donation = Donasi("",0,0,"","https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/195px-No-Image-Placeholder.svg.png",0)
        dataBinding.createDonationListener = this
    }

    override fun onCreateDonation(view: View){
        var title = dataBinding.txtCreateDonateTitle.text.toString()
        var desc = dataBinding.txtCreateDonationDesc.text.toString()
        var img = dataBinding.txtCreateDonateImgLink.text.toString()
        var goalval = dataBinding.txtCreateDonateGoalVal.text.toString().toInt()

        val donation = Donasi(title,0,goalval, desc,img,0)
        donationViewModel.createDonation(donation)

        val action = CreateDonationFragmentDirections.actionDonationBack()
        Navigation.findNavController(view).navigate(action)
    }
}
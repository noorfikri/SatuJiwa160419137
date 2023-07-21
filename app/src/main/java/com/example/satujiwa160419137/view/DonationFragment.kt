package com.example.satujiwa160419137.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.databinding.FragmentDonationBinding
import com.example.satujiwa160419137.model.Donasi
import com.example.satujiwa160419137.util.DonationBackListener
import com.example.satujiwa160419137.util.ValueDonationChangeListener
import com.example.satujiwa160419137.viewmodel.DonationDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class DonationFragment : Fragment(), ValueDonationChangeListener, DonationBackListener {
    private lateinit var donationDetailViewModel:DonationDetailViewModel
    private lateinit var dataBinding:FragmentDonationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_donation, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var donateID = ""


        if(arguments != null){
            donateID = DonationFragmentArgs.fromBundle(requireArguments()).donateId.toString()
        }

        dataBinding.donationBackListener = this
        dataBinding.valueDonationChangeListener =this
        dataBinding.donateVal = 1000

        donationDetailViewModel = ViewModelProvider(this).get(DonationDetailViewModel::class.java)
        donationDetailViewModel.get(donateID.toInt())

        observeDonateDetailViewModel(view)
    }

    fun observeDonateDetailViewModel(view: View){
        donationDetailViewModel.donationLD.observe(viewLifecycleOwner,{
            dataBinding.donation = it
        }
        )
    }

    override fun onValueDonationChange(v: View, donateValue: Int) {
        println("current donate val" + dataBinding.donateVal.toString())
        dataBinding.donateVal = v.tag.toString().toInt()
        println("current donate val" + dataBinding.donateVal.toString())
    }

    override fun onDonationBack(v: View, donation:Donasi, addValue: Int) {

        print(donation)
        println("current donate val" + donation.curVal.toString())
        println("add donate val" + addValue.toString())

        donationDetailViewModel.makeDonation(donation.title!!,donation.detail!!,donation.curVal!! + addValue,donation.goalVal!!,donation.img!!,donation.creator!! ,donation.id)

        print(donation)
        println("current donate val" + donation.curVal.toString())
        println("add donate val" + addValue.toString())


        val action = DonationFragmentDirections.actionMakeDonationBack(donation.id.toString())
        Navigation.findNavController(this.view!!).navigate(action)
    }
}
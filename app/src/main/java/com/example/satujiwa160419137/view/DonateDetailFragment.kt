package com.example.satujiwa160419137.view

import android.os.Bundle
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
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.model.AccountDAO
import com.example.satujiwa160419137.util.loadImage
import com.example.satujiwa160419137.viewmodel.DonationDetailViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DonateDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DonateDetailFragment : Fragment() {
    private lateinit var donateDetailViewModel:DonationDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donate_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var donateID = ""

        if(arguments!=null){
            donateID = DonateDetailFragmentArgs.fromBundle(requireArguments()).donateID
        }

        donateDetailViewModel = ViewModelProvider(this).get(DonationDetailViewModel::class.java)
        donateDetailViewModel.get(donateID)

        observeDonateDetailViewModel(view)

        val btnDonasiDetail = view.findViewById<Button>(R.id.btnDonasiDetail)
        btnDonasiDetail.setOnClickListener{
            val action = DonateDetailFragmentDirections.actionDonationDetailBack()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeDonateDetailViewModel(view: View){
        donateDetailViewModel.donationLD.observe(viewLifecycleOwner,{
            val txtTitle = view.findViewById<TextView>(R.id.txtTitleDonateDetail)
            val txtCurVal = view.findViewById<TextView>(R.id.txtCurrDonate3)
            val txtGoalVal = view.findViewById<TextView>(R.id.txtGoalDonate3)
            val txtDetail = view.findViewById<TextView>(R.id.txtDonateDetail)
            val txtCreator = view.findViewById<TextView>(R.id.txtCreatorDonateDetail)
            val imgDonate = view.findViewById<ImageView>(R.id.imgViewDonateDetail)
            val imgCreator = view.findViewById<ImageView>(R.id.imgDonateCreatorDetail)

            donateDetailViewModel.getCreator(it.creator!!)

            donateDetailViewModel.donationAccountLD.observe(viewLifecycleOwner,{
                txtCreator.text = it!!.username.toString()
                imgCreator.loadImage(it?.imgUrl)
            })

            txtTitle.text = it.title.toString()
            txtCurVal.text = it.curVal.toString()
            txtGoalVal.text = it.goalVal.toString()
            txtDetail.text = it.detail.toString()

            imgDonate.loadImage(it.img)
        })
    }
}
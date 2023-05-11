package com.example.satujiwa160419137.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.viewmodel.AccountLoginViewModel
import com.example.satujiwa160419137.viewmodel.DonationListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DonateListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DonateListFragment : Fragment() {
    private lateinit var donateListViewModel: DonationListViewModel
    private val  donateListAdapter = DonateListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donate_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedFile = "com.example.satujiwa160419137"
        val sharedPref = activity!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        if(sharedPref.getString("LOGGED_ID","")==""){
            val action = DonateListFragmentDirections.actionLogoutHome()
            Navigation.findNavController(view).navigate(action)
        }

        val recView = view.findViewById<RecyclerView>(R.id.recViewDonateList)

        donateListViewModel = ViewModelProvider(this).get(DonationListViewModel::class.java)
        donateListViewModel.getDonation()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = donateListAdapter

        observeDonateListViewModel(view)
    }

    fun observeDonateListViewModel(view: View){
        donateListViewModel.donateLD.observe(viewLifecycleOwner,{
            donateListAdapter.updateDonateList(it)
        })
        donateListViewModel.loadingLD.observe(viewLifecycleOwner,{
            val progLoad = view.findViewById<ProgressBar>(R.id.progBarDonateList)
            val recView = view.findViewById<RecyclerView>(R.id.recViewDonateList)
            if(it == true){
                recView.visibility = View.GONE
                progLoad.visibility = View.VISIBLE
            }else{
                recView.visibility = View.VISIBLE
                progLoad.visibility = View.GONE
            }
        })
    }
}
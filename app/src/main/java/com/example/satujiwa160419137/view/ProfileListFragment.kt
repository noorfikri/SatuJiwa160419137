package com.example.satujiwa160419137.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.viewmodel.AccountListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileListFragment : Fragment() {
    private lateinit var accountListViewModel: AccountListViewModel
    private val accountListAdapter = ProfileListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recView = view.findViewById<RecyclerView>(R.id.recViewProfileList)

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = accountListAdapter

        accountListViewModel = ViewModelProvider(this).get(AccountListViewModel::class.java)
        accountListViewModel.fetchAccounts()

        observeAccountListViewModel(view)
    }

    fun observeAccountListViewModel(view: View){
        accountListViewModel.accountsLD.observe(viewLifecycleOwner,{
            accountListAdapter.updateAccountList(it)
        })
        accountListViewModel.loadingLD.observe(viewLifecycleOwner,{
            val progressLoad = view.findViewById<ProgressBar>(R.id.progLoadProfileList)
            val recView = view.findViewById<RecyclerView>(R.id.recViewProfileList)

            if(it == true){
                recView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            }else{
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        })
    }
}
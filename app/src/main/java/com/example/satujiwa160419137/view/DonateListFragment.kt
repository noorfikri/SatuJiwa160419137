package com.example.satujiwa160419137.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.viewmodel.AccountLoginViewModel

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
    private lateinit var accountViewModel: AccountLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donate_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountViewModel = ViewModelProvider(this).get(AccountLoginViewModel::class.java)

        observeLoginViewModel(view)
    }


    fun observeLoginViewModel(view: View){
        accountViewModel.loginAccountLD.observe(viewLifecycleOwner,{
            Log.d("checklogin","checking login")
            if(it == null){
                val action = DonateListFragmentDirections.actionLogoutHome()
                Navigation.findNavController(view).navigate(action)
                Log.d("checklogin","account not loged in")
            }else if (it != null){
                Log.d("checklogin","account logged")
            }
        })
    }
}
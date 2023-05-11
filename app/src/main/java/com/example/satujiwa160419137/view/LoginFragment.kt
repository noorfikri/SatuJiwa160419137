package com.example.satujiwa160419137.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.viewmodel.AccountListViewModel
import com.example.satujiwa160419137.viewmodel.AccountLoginViewModel
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var accountsViewModel: AccountListViewModel
    private lateinit var loginAccountViewModel: AccountLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtUsername = view.findViewById<TextInputEditText>(R.id.txtUsernameLogin)
        val txtPassword = view.findViewById<TextInputEditText>(R.id.txtPasswordLogin)
        val btnLogin = view.findViewById<Button>(R.id.btnToLogin)
        val btnRegister = view.findViewById<Button>(R.id.btnToRegister)

        accountsViewModel = ViewModelProvider(this).get(AccountListViewModel::class.java)
        loginAccountViewModel = ViewModelProvider(this).get(AccountLoginViewModel::class.java)
        accountsViewModel.fetchAccounts()

        btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionRegisterAccount()
            Navigation.findNavController(it).navigate(action)
        }

        btnLogin.setOnClickListener {
            doLogin(txtUsername.text.toString(),txtPassword.text.toString(),view)
        }
    }

    fun doLogin(username:String,password:String, view:View){
        accountsViewModel.accountsLD.observe(viewLifecycleOwner,{
            it.forEach {
                if(it.username==username){
                    if(it.password==password){
                        loginAccountViewModel.login(it)

                        val action = LoginFragmentDirections.actionLogin()
                        Navigation.findNavController(view).navigate(action)
                    }
                }
            }
        })
    }

}
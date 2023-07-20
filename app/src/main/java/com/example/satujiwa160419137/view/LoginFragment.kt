package com.example.satujiwa160419137.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.databinding.FragmentLoginBinding
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.util.LoginButtonListener
import com.example.satujiwa160419137.util.RegisterButtonListener
import com.example.satujiwa160419137.viewmodel.AccountListViewModel
import com.example.satujiwa160419137.viewmodel.AccountLoginViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), RegisterButtonListener, LoginButtonListener {
    private lateinit var accountsViewModel: AccountListViewModel
    private lateinit var loginAccountViewModel: AccountLoginViewModel

    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val txtUsername = view.findViewById<TextInputEditText>(R.id.txtUsernameLogin)
//        val txtPassword = view.findViewById<TextInputEditText>(R.id.txtPasswordLogin)
//        val btnLogin = view.findViewById<Button>(R.id.btnToLogin)
//        val btnRegister = view.findViewById<Button>(R.id.btnToRegister)

//        accountsViewModel = ViewModelProvider(this).get(AccountListViewModel::class.java)
        loginAccountViewModel = ViewModelProvider(this).get(AccountLoginViewModel::class.java)

        dataBinding.user = Account("","","")
        dataBinding.loginListener = this
        dataBinding.registerListener = this
//        accountsViewModel.fetchAccounts()

//        btnLogin.setOnClickListener {
//            doLogin(txtUsername.text.toString(),txtPassword.text.toString(),view)
//        }

//        btnRegister.setOnClickListener {
//            val action = LoginFragmentDirections.actionRegisterAccount()
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    fun doLogin(view:View){
        val sharedFile = "com.example.satujiwa160419137"
        val sharedPref = activity!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        loginAccountViewModel.loginAccountLD.observe(viewLifecycleOwner,{
            val data = loginAccountViewModel.loginAccountLD.value

            if(data != null){
                sharedPref.edit().putString("LOGGED_ID",data.id.toString()).apply()

                val action = LoginFragmentDirections.actionLogin()
                Navigation.findNavController(view).navigate(action)
            }else{
                Toast.makeText(view.context,"Proses Login Gagal", Toast.LENGTH_LONG).show()
            }
        })
//            it.forEach {
//                if(it.username==username){
//                    if(it.password==password){
//                        sharedPref.edit().putString("LOGGED_ID",it.id.toString()).apply()
//
//                        val action = LoginFragmentDirections.actionLogin()
//                        Navigation.findNavController(view).navigate(action)
//                    }
//                }
//            }
//        })
    }

    override fun onLoginButton(v: View) {
        val txtUsername = dataBinding.txtUsernameLogin.text.toString()
        val txtPassword = dataBinding.txtPasswordLogin.text.toString()

        loginAccountViewModel.checkLogin(txtUsername, txtPassword)
        Log.d("Username salah","password salah")
        doLogin(v)
    }

    override fun onRegisterButton(v: View) {
        val action = LoginFragmentDirections.actionRegisterAccount()
        Navigation.findNavController(v).navigate(action)
    }

}
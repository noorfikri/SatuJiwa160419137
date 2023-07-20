package com.example.satujiwa160419137.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.databinding.FragmentRegisterBinding
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.util.RegisterButtonListener
import com.example.satujiwa160419137.viewmodel.AccountLoginViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(), RegisterButtonListener {
    private lateinit var loginAccountViewModel: AccountLoginViewModel
    private lateinit var dataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,R.layout.fragment_register, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginAccountViewModel = ViewModelProvider(this).get(AccountLoginViewModel::class.java)
        dataBinding.user = Account("","","")
        dataBinding.registerListener= this
//        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
//
//        btnRegister.setOnClickListener {
//            val action = RegisterFragmentDirections.actionRegisterBack()
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun onRegisterButton(v: View) {
        val username = dataBinding.txtUsernameRegsiter.text.toString()
        val password = dataBinding.txtPasswordRegister.text.toString()
        val repassword = dataBinding.txtRePasswordRegister.text.toString()

        if(password == repassword){
            val user = Account(username, password, "")
            loginAccountViewModel.registerUser(user)

            val action = RegisterFragmentDirections.actionRegisterBack()
            Navigation.findNavController(v).navigate(action)
        }
        else{
            Toast.makeText(v.context,"Proses Register Gagal",Toast.LENGTH_LONG).show()
        }
    }
}
package com.example.satujiwa160419137.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.databinding.DonateListItemBinding
import com.example.satujiwa160419137.databinding.HistoryListItemBinding
import com.example.satujiwa160419137.model.Account
import com.example.satujiwa160419137.model.History
import com.example.satujiwa160419137.util.loadImage

class ProfileListAdapter(val historyList:ArrayList<History>):RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder>() {
    class ProfileViewHolder(var view:HistoryListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileListAdapter.ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<HistoryListItemBinding>(inflater, R.layout.history_list_item, parent, false)

//        val view = inflater.inflate(R.layout.profile_list_item,parent,false)

        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileListAdapter.ProfileViewHolder, position: Int) {
        holder.view.history = historyList[position]

    //        val txtUsername = holder.view.findViewById<TextView>(R.id.txtProfileListUsername)
//        val imgProfile = holder.view.findViewById<ImageView>(R.id.imgProfileList)
//        val btnDetail = holder.view.findViewById<Button>(R.id.btnProfileDetail)
//
//        val accountID = accountList[position].id
//
//        txtUsername.text = accountList[position].username.toString()
//        imgProfile.loadImage(accountList[position].imgUrl)
//
//        btnDetail.setOnClickListener {
//            val action = ProfileListFragmentDirections.actionProfileDetail(accountID!!)
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun updateHistoryList(newHistoryList: ArrayList<History>){
        historyList.clear()
        historyList.addAll(newHistoryList)
        notifyDataSetChanged()
    }

}
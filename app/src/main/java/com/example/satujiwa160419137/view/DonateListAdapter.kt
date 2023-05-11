package com.example.satujiwa160419137.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.model.Donate

class DonateListAdapter(val donateList:ArrayList<Donate>) : RecyclerView.Adapter<DonateListAdapter.DonateViewHolder>() {
    class DonateViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DonateListAdapter.DonateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.donate_list_item,parent,false)

        return DonateViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonateListAdapter.DonateViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return donateList.size
    }
}
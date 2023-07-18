package com.example.satujiwa160419137.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.satujiwa160419137.R
import com.example.satujiwa160419137.model.Donasi
import com.example.satujiwa160419137.util.loadImage

class DonateListAdapter(val donateList:ArrayList<Donasi>) : RecyclerView.Adapter<DonateListAdapter.DonateViewHolder>() {
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
        val txtTitle = holder.view.findViewById<TextView>(R.id.txtTitleDonate)
        val txtCurVal = holder.view.findViewById<TextView>(R.id.txtCurrDonate)
        val txtGoalVal = holder.view.findViewById<TextView>(R.id.txtGoalDonate)
        val txtCreator = holder.view.findViewById<TextView>(R.id.txtCreatorDonate)
        val imgDonate = holder.view.findViewById<ImageView>(R.id.imgViewDonate)
        val imgCreator = holder.view.findViewById<ImageView>(R.id.imgDonateCreator)

        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progBarDonateList)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDonationDetail)

        var donateID = donateList[position].id.toString()

        txtTitle.text = donateList[position].title.toString()
        txtCurVal.text = donateList[position].curVal.toString()
        txtGoalVal.text = donateList[position].goalVal.toString()
        txtCreator.text = donateList[position].creator!!.username.toString()

        imgDonate.loadImage(donateList[position].img)
        imgCreator.loadImage(donateList[position].creator!!.imgUrl)

        btnDetail.setOnClickListener {
            val action = DonateListFragmentDirections.actionDetailDonation(donateID!!)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return donateList.size
    }

    fun updateDonateList(newDonateList: ArrayList<Donasi>){
        donateList.clear()
        donateList.addAll(newDonateList)
        notifyDataSetChanged()
    }
}
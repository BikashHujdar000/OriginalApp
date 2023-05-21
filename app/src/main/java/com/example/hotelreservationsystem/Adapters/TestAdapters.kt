package com.example.hotelreservationsystem.Adapters

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.TestModels.DataModel

class TestAdapters (val context :Context,val data: List<DataModel>):RecyclerView.Adapter<TestAdapters.MyViewHolder>() {

lateinit var  mlistner :onItemClickListner
       interface onItemClickListner{
           fun onItemClick(position: Int)
       }

    fun setOnItemClickListner(listner: onItemClickListner)
    {
        mlistner = listner
    }
    class MyViewHolder(itemview: View,listner: onItemClickListner) : RecyclerView.ViewHolder(itemview)
    {

        val name = itemview.findViewById<TextView>(R.id.hotel_sample_name)
        val location = itemview.findViewById<TextView>(R.id.hotel_sample_country_name)
        init {
            itemview.setOnClickListener {
                listner.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.test_layout_for_model_view,parent,false)
        return  MyViewHolder(view,mlistner)
    }

    override fun getItemCount(): Int {
            return  data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.name.text = data.get(position).name
        holder.location.text = data.get(position).location


    }

}


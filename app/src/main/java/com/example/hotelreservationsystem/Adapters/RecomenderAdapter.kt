package com.example.hotelreservationsystem.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelreservationsystem.Models.RecomnderData
import com.example.hotelreservationsystem.R
import com.google.android.material.imageview.ShapeableImageView

class RecomenderAdapter(val context:Context, val data: List<RecomnderData>): RecyclerView.Adapter<RecomenderAdapter.MyViewHolder>() {

    lateinit var  mlistner :onItemClickListner
    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner: onItemClickListner)
    {
        mlistner = listner
    }


    class MyViewHolder (itemview: View,listner:onItemClickListner) : RecyclerView.ViewHolder(itemview)
    {
        val name = itemview.findViewById<TextView>(R.id.hotel_name_recommender)
        val location = itemview.findViewById<TextView>(R.id.hotel__recommender_loaction)
       val image = itemview.findViewById<ShapeableImageView>(R.id.hotelImage_recommeder)

        init {
            itemView.setOnClickListener{
                listner.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_for_recommender,parent,false)
        return  MyViewHolder(view,mlistner)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = data.get(position).name
        holder.location.text = data.get(position).address
        //  Glide.with(this.context).load(RoomData.get(position).img).into(holder.room_view_image)

        //yaha xu ma hain
        Glide.with(this.context).load(data.get(position).photos).into((holder.image))
    }

}

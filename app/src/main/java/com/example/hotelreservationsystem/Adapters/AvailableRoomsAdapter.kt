package com.example.hotelreservationsystem.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelreservationsystem.Models.Room
import com.example.hotelreservationsystem.R
import com.google.android.material.imageview.ShapeableImageView

class AvailableRoomsAdapter(val context : Context,  val Rooms:List<Room>): RecyclerView.Adapter<AvailableRoomsAdapter.MyViewHolder>()  {
    class MyViewHolder( itemView: View): RecyclerView.ViewHolder(itemView) {
        val room_number = itemView.findViewById<TextView>(R.id.room_view_number)
        val room_view_type = itemView.findViewById<TextView>(R.id.room_view_type)
        val room_view_price = itemView.findViewById<TextView>(R.id.room_view_price)
        val room_view_image=itemView.findViewById<ShapeableImageView>(R.id.roomViewImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_room_view_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
      return Rooms.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.room_number.text = Rooms.get(position).number.toString()
        holder.room_view_type.text = Rooms.get(position).type.toString()
        holder.room_view_price.text = Rooms.get(position).price.toString()
        Glide.with(this.context)
            .load(Rooms!!.get(position).img[0]).into(holder.room_view_image)


        }
    }
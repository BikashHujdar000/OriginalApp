package com.example.hotelreservationsystem.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelreservationsystem.Models.Hotel
import com.example.hotelreservationsystem.R

class TestAdapters(val context:Context, val data: List<Hotel>?):RecyclerView.Adapter<TestAdapters.MyViewHolder>() {


    lateinit var mlistner: onItemClickListner

    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner: onItemClickListner) {
        mlistner = listner
    }

    class MyViewHolder(itemview: View, listner: onItemClickListner) :
        RecyclerView.ViewHolder(itemview) {
        // defining the holder operation
        val name = itemview.findViewById<TextView>(R.id.hotel_sample_name)
        val location = itemview.findViewById<TextView>(R.id.hotel_sample_country_name)
        val image = itemview.findViewById<ImageView>(R.id.room_image)
        val rating = itemview.findViewById<RatingBar>(R.id.ratingBar)
        val cheapPrice = itemview.findViewById<TextView>(R.id.cheapestPrice)

        init {
            itemview.setOnClickListener {
                listner.onItemClick(adapterPosition)

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // inflating the model layout to the holder
        val view =
            LayoutInflater.from(context).inflate(R.layout.test_layout_for_model_view, parent, false)
        return MyViewHolder(view, mlistner)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // setting name and location from the adapter to the holder
        holder.name.text = data!!.get(position).name
        holder.location.text = data!!.get(position).address
        if (data!!.get(position).photos.isEmpty()) {
            Glide.with(this.context)
                .load("https://res.cloudinary.com/dancvkguq/image/upload/v1684861705/hotel-images/qaeybsnbjkmggtuyv9a9.png")
                .into(holder.image)
        } else {
            Glide.with(this.context)
                .load(data!!.get(position).photos[0]).into(holder.image)
        }
        holder.cheapPrice.text = data.get(position).cheapestPrice.toString()
        var ratingNum = data.get(position).rating.toString()
        val f1 = ratingNum.toFloat()
        holder.rating.rating= f1


    }
}








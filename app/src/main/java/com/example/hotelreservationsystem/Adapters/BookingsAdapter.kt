package com.example.hotelreservationsystem.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelreservationsystem.Models.Booking
import com.example.hotelreservationsystem.R
import org.w3c.dom.Text

class BookingsAdapter(val context:Context,val bookingData : List<Booking>):RecyclerView.Adapter<BookingsAdapter.MyViewHolder>() {

    inner class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val hotel_name = itemView.findViewById<TextView>(R.id.user_book_hotel_name)
        val room_number = itemView.findViewById<TextView>(R.id.user_book_room_no)
        val checkinDate = itemView.findViewById<TextView>(R.id.user_book_check_in_date)
        val checkoutDate = itemView.findViewById<TextView>(R.id.user_book_check_out_date)
        val cancelbooking = itemView.findViewById<TextView>(R.id.user_cancel_booking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.user_bookings_view_layout,parent,false)
        return  MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookingData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.hotel_name.text = bookingData.get(position).hotel.name.toString()
        holder.room_number.text = bookingData.get(position).room.number.toString()
        holder.checkinDate.text = bookingData.get(position).startDate.toString()
        holder.checkoutDate.text = bookingData.get(position).endDate.toString()

    }


}

//class ReviewsAdapterTest(val context : Context, val data: List<ReviewModel>): RecyclerView.Adapter<ReviewsAdapterTest.MyViewHolder>() {
//
//   inner class MyViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview)
//    {
//        val name = itemview.findViewById<TextView>(R.id.user_name)
//        val date= itemview.findViewById<TextView>(R.id.review_date)
//        val user_image =  itemview.findViewById<ShapeableImageView>(R.id.shapeableImageView)
//        val description =  itemview.findViewById<TextView>(R.id.review)
//        val rate =  itemview.findViewById<TextView>(R.id.rate_num)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.reviews_layout_resources,parent,false)
//        return  MyViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return  data.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val lb =data[position];
//        holder.name.text = data.get(position).name.toString()
//        holder.date.text= data.get(position).date.toString()
//       holder.user_image.setImageResource(lb.profile_picture)
//        holder.rate.text = lb.rate.toString()
//        holder.description.text = lb.review.toString()
//
//
//
//    }
//
//}
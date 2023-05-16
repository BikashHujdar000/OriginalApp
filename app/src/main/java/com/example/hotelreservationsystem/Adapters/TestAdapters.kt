package com.example.hotelreservationsystem.Adapters

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.TestModels.DataModel
import org.w3c.dom.Text

class TestAdapters (val context :Context,val data: List<DataModel>):RecyclerView.Adapter<TestAdapters.MyViewHolder>() {

    class MyViewHolder (itemview:View) : RecyclerView.ViewHolder(itemview)
    {
        val name = itemview.findViewById<TextView>(R.id.hotel_sample_name)
        val location = itemview.findViewById<TextView>(R.id.hotel_sample_country_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.test_layout_for_model_view,parent,false)
        return  MyViewHolder(view)
    }

    override fun getItemCount(): Int {
            return  data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.name.text = data.get(position).name
        holder.location.text = data.get(position).location
    }

}




//lass NewsAdapter(val context: Context,val article: List<Article>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>(){
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.smaple,parent,false)
//        return  MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.title.text=article[position].title
//        holder.description.text=article[position].description
//        Glide.with(context).load(article[position].urlToImage).into(holder.image)
//
//    }
//
//    override fun getItemCount(): Int {
//        return  article.size
//    }
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val title = itemView.findViewById<TextView>(R.id.txt_title)
//        val description = itemView.findViewById<TextView>(R.id.txt_description)
//        val image= itemView.findViewById<ImageView>(R.id.imageView2)
//
//    }
//
//
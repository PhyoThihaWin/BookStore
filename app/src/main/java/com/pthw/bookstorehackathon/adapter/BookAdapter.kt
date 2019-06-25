package com.pthw.bookstorehackathon.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.developer.pthw.retrofittest.Api.ApiClient
import com.pthw.bookstorehackathon.R
import com.pthw.bookstorehackathon.model.Book

class BookAdapter(var context: Context, var booklist: List<Book>) :
    RecyclerView.Adapter<BookAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.all_item_list, viewGroup, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val book = booklist.get(i)
        myViewHolder.title.text = book.title
        myViewHolder.price.text = book.price

        Glide.with(context)
            .load(ApiClient.BASE_URL + book.image)
            .apply(
                RequestOptions()
                    .centerCrop()
                    .dontAnimate()
            ).into(myViewHolder.image)

    }

    override fun getItemCount(): Int {
        return booklist.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MyViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var price: TextView
        var image: ImageView

        init {

            title = itemView.findViewById(R.id.title)
            price = itemView.findViewById(R.id.price)
            image = itemView.findViewById(R.id.image)

        }
    }
}

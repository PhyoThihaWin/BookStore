//package com.pthw.bookstorehackathon.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.paging.PagedListAdapter
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.pthw.bookstorehackathon.R
//import com.pthw.bookstorehackathon.model.Book
//
//class BookPagingAdapter(var context: Context) : PagedListAdapter<Book, BookPagingAdapter.MyViewHolder>(BookDiffCallback) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookPagingAdapter.MyViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.all_item_list, parent, false)
//
//        return MyViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: BookPagingAdapter.MyViewHolder, position: Int) {
//
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return position
//    }
//
//    companion object {
//        val BookDiffCallback = object : DiffUtil.ItemCallback<Book>() {
//            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
//                return oldItem.title == newItem.title
//            }
//
//            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//
//
//    class MyViewHolder
//        (itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var txtOne: TextView
//        var txtTwo: TextView
//        var txtDie: TextView
//        var img1: ImageView
//        var img2: ImageView
//
//        init {
//
//            txtOne = itemView.findViewById(R.id.txtOne)
//            txtTwo = itemView.findViewById(R.id.txtTwo)
//            txtDie = itemView.findViewById(R.id.txtDie)
//            img1 = itemView.findViewById(R.id.img1)
//            img2 = itemView.findViewById(R.id.img2)
//
//        }
//    }
//
//
//}
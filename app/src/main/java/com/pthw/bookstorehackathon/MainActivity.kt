package com.pthw.bookstorehackathon

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.pthw.retrofittest.Api.Api
import com.developer.pthw.retrofittest.Api.ApiClient
import com.pthw.bookstorehackathon.adapter.BookAdapter
import com.pthw.bookstorehackathon.model.Book
import com.pthw.bookstorehackathon.model.ServerResult
import kotlinx.android.synthetic.main.recycler_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var bookList: List<Book>
    lateinit var loading: ProgressDialog
    val api: Api = ApiClient.client.create(Api::class.java)
    lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.setHasFixedSize(true)
        var mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recycler.layoutManager = mLayoutManager
        getFoods() //--recycler first bind from api

    }

    fun getFoods() {


        loading = ProgressDialog.show(this, "Retrieving data..", "", false, false)
        bookList = emptyList()

        val call = api.getBook()
        call.enqueue(object : Callback<ServerResult> {
            override fun onResponse(call: Call<ServerResult>, response: Response<ServerResult>) {

                bookList = response.body()!!.result!!         //displaying the string array into listview
                bookAdapter = BookAdapter(this@MainActivity, bookList)
                recycler.adapter = bookAdapter
                loading.dismiss()
            }

            override fun onFailure(call: Call<ServerResult>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT)
                loading.dismiss()

            }
        })
    }
}

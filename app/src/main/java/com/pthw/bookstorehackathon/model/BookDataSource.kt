package com.pthw.bookstorehackathon.model

import android.content.ClipData.Item
import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//class BookDataSource : PageKeyedDataSource<Int, Item>() {

//    companion object {
//
//        //the size of a page that we want
//        val PAGE_SIZE = 50
//
//        //we will start from the first page which is 1
//        private val FIRST_PAGE = 1
//
//        //we need to fetch from stackoverflow
//        private val SITE_NAME = "stackoverflow"
//    }
//
//
//    //this will be called once to load the initial data
//    override fun loadInitial(
//        params: PageKeyedDataSource.LoadInitialParams<Int>,
//        callback: PageKeyedDataSource.LoadInitialCallback<Int, Item>
//    ) {
//        RetrofitClient.getInstance()
//            .getApi().getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
//            .enqueue(object : Callback<StackApiResponse> {
//                override fun onResponse(call: Call<StackApiResponse>, response: ServerResult<StackApiResponse>) {
//                    if (response.body() != null) {
//                        callback.onResult(response.body()!!.items, null, FIRST_PAGE + 1)
//                    }
//                }
//
//                override fun onFailure(call: Call<StackApiResponse>, t: Throwable) {
//
//                }
//            })
//    }
//
//    //this will load the previous page
//    override fun loadBefore(
//        params: PageKeyedDataSource.LoadParams<Int>,
//        callback: PageKeyedDataSource.LoadCallback<Int, Item>
//    ) {
//        RetrofitClient.getInstance()
//            .getApi().getAnswers(params.key, PAGE_SIZE, SITE_NAME)
//            .enqueue(object : Callback<StackApiResponse> {
//                override fun onResponse(call: Call<StackApiResponse>, response: ServerResult<StackApiResponse>) {
//
//                    //if the current page is greater than one
//                    //we are decrementing the page number
//                    //else there is no previous page
//                    val adjacentKey = if (params.key > 1) params.key - 1 else null
//                    if (response.body() != null) {
//
//                        //passing the loaded data
//                        //and the previous page key
//                        callback.onResult(response.body()!!.items, adjacentKey)
//                    }
//                }
//
//                override fun onFailure(call: Call<StackApiResponse>, t: Throwable) {
//
//                }
//            })
//    }
//
//    //this will load the next page
//    override fun loadAfter(
//        params: PageKeyedDataSource.LoadParams<Int>,
//        callback: PageKeyedDataSource.LoadCallback<Int, Item>
//    ) {
//        RetrofitClient.getInstance()
//            .getApi()
//            .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
//            .enqueue(object : Callback<StackApiResponse> {
//                override fun onResponse(call: Call<StackApiResponse>, response: ServerResult<StackApiResponse>) {
//
//                    if (response.body() != null) {
//                        //if the response has next page
//                        //incrementing the next page number
//                        val key = if (response.body()!!.has_more) params.key + 1 else null
//
//                        //passing the loaded data and next page value
//                        callback.onResult(response.body()!!.items, key)
//                    }
//                }
//
//                override fun onFailure(call: Call<StackApiResponse>, t: Throwable) {
//
//                }
//            })
//    }
//
//}
//}
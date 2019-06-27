package com.developer.pthw.retrofittest.Api

import com.pthw.bookstorehackathon.model.ServerResult
import retrofit2.Call
import retrofit2.http.*
import okhttp3.ResponseBody
import retrofit2.http.Streaming
import retrofit2.http.GET
import retrofit2.http.Url




/**
 * Created by Belal on 10/2/2017.
 */

interface Api {

    @GET("api/book")
    // @FormUrlEncoded
    fun getBook(): Call<ServerResult>

    //--download interface with dynamic URL
    @GET
    fun downloadFileWithDynamicUrlSync(@Url fileUrl: String): Call<ResponseBody>


//    @POST("DiFood_php/")
//    @FormUrlEncoded
//    fun getSearchItem(@Field("type") type: String, @Field("word") item: String): Call<List<Book>>
//
//    @POST("DiFood_php/")
//    @FormUrlEncoded
//    fun checkUpdate(@Field("type") type: String): Call<String>


}


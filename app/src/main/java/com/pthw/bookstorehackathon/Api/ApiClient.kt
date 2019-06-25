package com.developer.pthw.retrofittest.Api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        val BASE_URL = "http://192.168.65.112/bookstore_project/"
        var retrofit: Retrofit? = null

        //Here we are using the GsonConverterFactory to directly convert json data to object
        val client: Retrofit
            get() {

                if (retrofit == null) {

                    val okHttpClient = OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build()

                    val gson = GsonBuilder()
                        .setLenient()
                        .create()

                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                }
                return retrofit!!
            }

    }
}
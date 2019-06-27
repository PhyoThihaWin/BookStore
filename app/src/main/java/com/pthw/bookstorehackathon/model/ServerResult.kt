package com.pthw.bookstorehackathon.model

import com.google.gson.annotations.SerializedName

data class ServerResult(
    @SerializedName("result")
    val result: List<Book>? = null,

    @SerializedName("author")
    val author: List<Author>? = null
)
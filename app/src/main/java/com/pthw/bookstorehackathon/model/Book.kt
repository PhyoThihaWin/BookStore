package com.pthw.bookstorehackathon.model

data class Book(
    val title: String,
    val price: String,
    val image: String,
    val description: String,
    val author_id: String,
    val publisher_id: String,
    val genre_id: String,
    val author: Author,
    val publisher: Publisher,
    val genre: Genre,
    val pdf: String
)

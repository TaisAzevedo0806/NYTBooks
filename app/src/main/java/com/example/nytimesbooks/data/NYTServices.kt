package com.example.nytimesbooks.data

import com.example.nytimesbooks.data.response.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "vyDdCXQ5RYQMdFVRRHtEGF2Xa7wUvH2h",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookResponse>

}
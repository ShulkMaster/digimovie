package com.sovize.labomovie.client.drivers

import com.sovize.labomovie.database.entities.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieData {

    @GET("/")
    fun searchTitle(@Query("s") title: String): Call<List<Movie>>

}
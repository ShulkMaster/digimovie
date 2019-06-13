package com.sovize.labomovie.client.drivers

import com.sovize.labomovie.client.models.ServerResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieData {

    @GET("/")
    fun searchTitleAsync(@Query("s") title: String): Deferred<Response<ServerResponse>>

}
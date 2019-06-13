package com.sovize.labomovie.repositories

import android.util.Log
import com.sovize.labomovie.client.RetrofitCaller
import com.sovize.labomovie.client.drivers.MovieData
import com.sovize.labomovie.client.models.ServerResponse
import com.sovize.labomovie.utils.AppLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    fun movieSearch(title: String) {
        val caller = RetrofitCaller.getInstance(MovieData::class.java)
        caller.searchTitle(title).enqueue(object : Callback<ServerResponse> {

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                Log.d(AppLogger.retro, "Error de API", t)
            }

            override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                when (response.code()) {
                    200 -> {
                        Log.d(AppLogger.retro, "Funciono ${response.body()}")
                        response.body()?.Search?.forEach {
                            Log.d(AppLogger.retro, "Funciono $it")
                        }
                    }
                    else -> {
                        Log.d(AppLogger.retro, "Se conecto pero  ${response.errorBody()}")
                    }
                }
            }

        })


    }

}
package com.sovize.labomovie.client

import com.sovize.labomovie.utils.ServerInfo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCaller {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("apikey", ServerInfo.apiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val customClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(customClient)
        .baseUrl(ServerInfo.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <E : Any> getIntance(masterClass: E) : E =
        retrofit().create(masterClass::class.java)

}
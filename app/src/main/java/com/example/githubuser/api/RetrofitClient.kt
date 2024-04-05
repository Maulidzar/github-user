package com.example.githubuser.api

import com.example.githubuser.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val client = OkHttpClient.Builder().addInterceptor {
        it.proceed (
            it
                .request()
                .newBuilder()
                .apply { addHeader("Authorization","Token ${BuildConfig.KEY}") }
                .build()
        )
    } .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance = retrofit.create(Api::class.java)
}
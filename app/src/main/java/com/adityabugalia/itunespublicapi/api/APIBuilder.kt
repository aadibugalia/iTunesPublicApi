package com.adityabugalia.itunespublicapi.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIBuilder {
    val BUILDSTATEDEBUG = true

    fun getRetrofit(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(APIConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getLogInterceptorClient())
            .build()
    }

    fun getLogInterceptorClient(): OkHttpClient? {
        val clientBuilder = OkHttpClient.Builder()
        if (BUILDSTATEDEBUG) {    // API logs in debug mode
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            clientBuilder.addInterceptor(interceptor)
        }
        return clientBuilder.build()
    }


}
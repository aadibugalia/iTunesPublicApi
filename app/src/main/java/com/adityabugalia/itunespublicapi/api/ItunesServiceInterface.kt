package com.adityabugalia.itunespublicapi.api

import com.adityabugalia.itunespublicapi.models.ResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesServiceInterface {

    @GET("search")
    fun getSearchResults(
        @Query("term") searchTerm: CharSequence?,
        @Query("limit") limit: String?
    ): Call<ResultModel?>?
}
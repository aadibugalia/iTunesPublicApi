package com.adityabugalia.itunespublicapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultModel(
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int,

    @SerializedName("results")
    @Expose
    var items : MutableList<SearchResultModel>
)
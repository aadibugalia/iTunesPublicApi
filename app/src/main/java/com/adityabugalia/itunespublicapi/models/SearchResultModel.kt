package com.adityabugalia.itunespublicapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResultModel(
    @SerializedName("artworkUrl30")
    @Expose
    var albumArtworkUrl30: String,
    @SerializedName("collectionName")
    @Expose
    var albumName: String,
    @SerializedName("releaseDate")
    @Expose
    var releaseDate: String,
    @SerializedName("primaryGenreName")
    @Expose
    var primaryGenreName: String,
    @SerializedName("collectionPrice")
    @Expose
    var collectionPrice: String,
    @SerializedName("currency")
    @Expose
    var currency: String,
    @SerializedName("country")
    @Expose
    var country: String
) {


    fun getAlbumArtWork(): String {
        return albumArtworkUrl30
    }

    fun getAName(): String {
        return albumName
    }

    fun getAlbumReleaseDate(): String {
        return releaseDate
    }
}

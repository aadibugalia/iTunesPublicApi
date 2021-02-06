package com.adityabugalia.itunespublicapi.ui

import com.adityabugalia.itunespublicapi.models.ResultModel
import com.adityabugalia.itunespublicapi.models.SearchResultModel

interface UiNotifications {
    fun notifyUI(searchResultModel: SearchResultModel)
}
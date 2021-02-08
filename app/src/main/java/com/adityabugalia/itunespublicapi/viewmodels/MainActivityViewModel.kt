package com.adityabugalia.itunespublicapi.viewmodels

import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adityabugalia.itunespublicapi.api.APIBuilder
import com.adityabugalia.itunespublicapi.api.ItunesServiceInterface
import com.adityabugalia.itunespublicapi.models.GenericResultModel
import com.adityabugalia.itunespublicapi.models.ResultModel
import com.adityabugalia.itunespublicapi.models.SearchResultModel
import com.adityabugalia.itunespublicapi.utils.ValidationUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*

class MainActivityViewModel : ViewModel() {

    var resultLiveData: MutableLiveData<Any> =
        MutableLiveData()
        private set
    lateinit var resultList: ResultModel
    private lateinit var iTunesService: ItunesServiceInterface
    fun resultListInitialised(): Boolean = ::resultList.isInitialized

    val FILTER_QUERY_DELAY: Long = 250

    private var timer: Timer? = null

    fun createSearchQueryListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                timer = Timer()
                timer!!.schedule(
                    object : TimerTask() {
                        override fun run() {
                            executeSearchFilter(newText)
                        }
                    },
                    FILTER_QUERY_DELAY
                )
                return false
            }
        }
    }

    private fun executeSearchFilter(newText: String) {
        if (ValidationUtils.validateSearchInput(newText)) {
            //call API
            callAPI(newText)
        } else if (newText.length == 0) {
            resultList.items.clear()
            resultLiveData.postValue(GenericResultModel(false, ""))

        }
    }

    fun onRecyclerViewItemClicked(model: SearchResultModel) {
        resultLiveData.postValue(model)

    }

    private fun callAPI(queryText: String) {
        iTunesService = APIBuilder.getRetrofit()!!.create(ItunesServiceInterface::class.java)
        iTunesService.getSearchResults(queryText, "25")
            ?.enqueue(object : Callback<ResultModel?> {

                override fun onFailure(call: Call<ResultModel?>, t: Throwable) {
                    resultLiveData.postValue(GenericResultModel(false, "no response recieved"))
                }

                override fun onResponse(
                    call: Call<ResultModel?>,
                    response: Response<ResultModel?>
                ) {
                    if (response?.isSuccessful) {
                        try {
                            resultList = response.body()!!
                            resultLiveData.postValue(response.body())
                        } catch (e: Exception) {
                            resultLiveData.postValue("Some Error Occured: Please Try Again Later.")
                        }

                    } else {
                        resultLiveData.postValue(GenericResultModel(false, "request failed."))

                    }
                }

            })
    }

}

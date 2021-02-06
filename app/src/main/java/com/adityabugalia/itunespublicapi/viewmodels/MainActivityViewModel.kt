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
    val FILTER_QUERY_TEXT_MIN_LENGTH = 5
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
        if (newText.length >= FILTER_QUERY_TEXT_MIN_LENGTH) {
            //call API
            callAPI(newText)
        }
    }
    fun onRecyclerViewItemClicked(model: SearchResultModel){
        resultLiveData.postValue(model)

    }
    private fun callAPI(queryText: String) {
        iTunesService = APIBuilder.getRetrofit()!!.create(ItunesServiceInterface::class.java)
        iTunesService.getSearchResults(queryText, "25")
            ?.enqueue(object : Callback<ResultModel?> {

                override fun onFailure(call: Call<ResultModel?>, t: Throwable) {
                    resultLiveData.postValue(GenericResultModel(false,"no response recieved"))
                }

                override fun onResponse(
                    call: Call<ResultModel?>,
                    response: Response<ResultModel?>
                ) {
                    if (response?.isSuccessful!!) {
                        resultList = response.body()!!
                        resultLiveData.postValue(response.body())
                    } else {
                        resultLiveData.postValue(GenericResultModel(false,"request failed."))

                    }
                }

            })
    }

}

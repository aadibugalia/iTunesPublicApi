package com.adityabugalia.itunespublicapi.viewmodels

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import java.util.*

class MainActivityViewModel : ViewModel() {

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
        }
    }
}
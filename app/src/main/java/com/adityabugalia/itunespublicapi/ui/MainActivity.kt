package com.adityabugalia.itunespublicapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.adityabugalia.itunespublicapi.R
import com.adityabugalia.itunespublicapi.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }


    override fun onResume() {
        super.onResume()

        searchAPiSearchBox?.setOnQueryTextListener(viewModel.createSearchQueryListener())
    }




    private fun clearSearchView() {
        searchAPiSearchBox?.let {
            it.setQuery("", false)
            it.onActionViewExpanded()
            it.clearFocus()
            it.isIconified
            it.setIconifiedByDefault(false)
        }
    }
}
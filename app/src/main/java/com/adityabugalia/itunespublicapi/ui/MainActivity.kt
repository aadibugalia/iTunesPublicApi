package com.adityabugalia.itunespublicapi.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityabugalia.itunespublicapi.R
import com.adityabugalia.itunespublicapi.adapters.MainListDisplayAdapter
import com.adityabugalia.itunespublicapi.models.GenericResultModel
import com.adityabugalia.itunespublicapi.models.ResultModel
import com.adityabugalia.itunespublicapi.models.SearchResultModel
import com.adityabugalia.itunespublicapi.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: MainListDisplayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onResume() {
        super.onResume()
        initViewModel()
        searchAPiSearchBox?.setOnQueryTextListener(viewModel.createSearchQueryListener())

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.resultLiveData.observe(
            this,
            Observer { result ->

                when (result) {
                    is ResultModel -> {
                        adapter.reloadData()
                    }
                    is SearchResultModel -> {
                        showDetailsDialog(result)
                    }
                    is GenericResultModel -> {
                        adapter.reloadData()
                        if(result.resultDescription.length>0)
                        showToast(result.resultDescription)
                    }

                }
                hideKeyboard()
            }
        )
        adapter = MainListDisplayAdapter(viewModel, this)
        val mLayoutManager = LinearLayoutManager(applicationContext)

        mainDisplayRV.adapter = adapter
        mainDisplayRV.setLayoutManager(mLayoutManager)
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

    private fun showDetailsDialog(searchResultModel: SearchResultModel) {
        AlertDialog.Builder(this)
            .setTitle(searchResultModel.albumName)
            .setMessage("Primary Genre Name: "+searchResultModel.primaryGenreName+"\n"+
            "Collection Price: "+searchResultModel.collectionPrice+"\n"+
            "Currency: "+ searchResultModel.currency+"\n"+
            "Copyright: "+searchResultModel.country)
            .setCancelable(false)
            .setPositiveButton("OK", null)
            .setOnDismissListener({ }).show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun hideKeyboard() {
        val imm = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.getRootView().windowToken, 0)
    }

}
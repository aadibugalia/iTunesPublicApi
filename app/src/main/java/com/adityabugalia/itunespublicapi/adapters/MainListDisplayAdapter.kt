package com.adityabugalia.itunespublicapi.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.itunespublicapi.viewbinders.MainListDisplayBinder
import com.adityabugalia.itunespublicapi.viewmodels.MainActivityViewModel


class MainListDisplayAdapter (val viewmodel: MainActivityViewModel, val ctx: Context) :
AbstractRecyclerViewAdapter<RecyclerView.ViewHolder>() {

    @Suppress("UNCHECKED_CAST")
    override fun buildRows() {
        listItems.clear()
        addData()
    }

    private fun addData() {
        viewmodel.resultList.items.forEach {
            listItems.add(MainListDisplayBinder(it, viewmodel) as Binder<RecyclerView.ViewHolder>)
        }
    }
}

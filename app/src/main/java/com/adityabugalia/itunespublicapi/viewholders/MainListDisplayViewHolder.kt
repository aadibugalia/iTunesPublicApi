package com.adityabugalia.itunespublicapi.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.itunespublicapi.BR
import com.adityabugalia.itunespublicapi.databinding.MainListItemStructureBinding
import com.adityabugalia.itunespublicapi.models.SearchResultModel

class MainListDisplayViewHolder(val binding: MainListItemStructureBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: SearchResultModel) {
        binding.setVariable(BR.searchResultModel,data)
        binding.executePendingBindings()
    }
}
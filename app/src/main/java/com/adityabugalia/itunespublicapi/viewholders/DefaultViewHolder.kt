package com.adityabugalia.itunespublicapi.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.itunespublicapi.R

class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun getLayoutId(): Int {
            return R.layout.empty_layout
        }
    }
}
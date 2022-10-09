package com.adityabugalia.itunespublicapi.viewbinders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.itunespublicapi.adapters.AbstractRecyclerViewAdapter
import com.adityabugalia.itunespublicapi.viewholders.DefaultViewHolder

class DefaultBinder : AbstractRecyclerViewAdapter.Binder<DefaultViewHolder> {

    override fun bind(holder: DefaultViewHolder) {
        // intentionally left empty.
    }

    override fun getViewType(): Int {
        return this.javaClass.hashCode()
    }

    override fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(DefaultViewHolder.getLayoutId(), parent, false)
        return DefaultViewHolder(view)
    }
}

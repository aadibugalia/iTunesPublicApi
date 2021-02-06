package com.adityabugalia.itunespublicapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.itunespublicapi.R
import com.adityabugalia.itunespublicapi.models.SearchResultModel
import com.adityabugalia.itunespublicapi.ui.UiNotifications
import com.adityabugalia.itunespublicapi.viewmodels.MainActivityViewModel
import com.bumptech.glide.Glide

class MainListDisplayAdapter(
    private val context: Context,
    val viewModel: MainActivityViewModel,
    val notifyUIListner: UiNotifications
) :
    RecyclerView.Adapter<MainListDisplayAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        if(viewModel.resultListInitialised())
        return viewModel.resultList.items.size
        else
            return  0
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.main_list_item_structure, viewGroup, false)
        )
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        val vh = viewHolder as ItemViewHolder
        vh.bind(viewModel.resultList.items.get(position))
    }

    open class ViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!)

    inner class ItemViewHolder(itemView: View) :
        ViewHolder(itemView), View.OnClickListener {

        private var albumName: TextView = itemView.findViewById(R.id.albumNameTV)
        private var albumReleaseDate: TextView = itemView.findViewById(R.id.albumReleaseDateTV)
        private var albumArt: ImageView = itemView.findViewById(R.id.albumArtIV)


        fun bind(searchResultModel: SearchResultModel) {
            albumName.text = searchResultModel.albumName
            albumReleaseDate.text = searchResultModel.releaseDate
            Glide.with(context)
                .load(searchResultModel.albumArtworkUrl30)
                .into(albumArt)

            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION && notifyUIListner != null) {
                notifyUIListner.notifyUI(viewModel.resultList.items.get(pos))
            }
        }


    }


}


package com.adityabugalia.itunespublicapi.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.itunespublicapi.viewbinders.DefaultBinder

@Suppress("UNCHECKED_CAST")
abstract class AbstractRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var listItems: ArrayList<Binder<T>> = ArrayList()


    @Suppress("UNCHECKED_CAST")
    abstract fun buildRows()

    fun reloadData() {
        buildRows()
        notifyDataSetChanged()
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewHolder(viewType: Int): Binder<T> {
        listItems.forEach {
            if (it.getViewType() == viewType) {
                return it
            }
        }
        return DefaultBinder() as Binder<T>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(viewType).createViewHolder(parent, viewType)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        listItems[position].bind(holder as T)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return listItems[position].getViewType()
    }

//    override fun getHeaderPositionForItem(itemPosition: Int): Int {
//        var headerPosition = 0
//        var currentItemPosition = itemPosition
//        do {
//            if (listItems[currentItemPosition].isHeaderView()) {
//                headerPosition = currentItemPosition
//                break
//            }
//            currentItemPosition -= 1
//        } while (currentItemPosition >= 0)
//        return headerPosition
//    }

//    override fun isHeader(itemPosition: Int): Boolean {
//        return listItems[itemPosition].isHeaderView()
//    }

//    override fun bindHeaderData(headerPosition: Int, parent: RecyclerView): T {
//        val viewHolder = listItems[headerPosition].createViewHolder(parent, listItems[headerPosition].getViewType()) as T
//        listItems[headerPosition].bind(
//            viewHolder
//        )
//        return viewHolder
//    }

//    override fun getHeaderLayout(headerPosition: Int): Int? {
//        return listItems[headerPosition].getLayout()
//    }

    interface Binder<T> {
        fun bind(holder: T)
        fun getViewType(): Int
        fun isHeaderView(): Boolean { return false }
        fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
        fun getLayout(): Int? { return null }
        fun getViewHolder(): T? { return null }
    }
}

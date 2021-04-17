package com.oratakashi.movieq.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oratakashi.movieq.BuildConfig
import com.oratakashi.movieq.R
import com.oratakashi.movieq.data.model.trailer.DataTrailer
import com.oratakashi.movieq.databinding.AdapterTrailerBinding
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.viewBinding

class TrailerAdapter : RecyclerView.Adapter<ViewHolder<AdapterTrailerBinding>>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterTrailerBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterTrailerBinding>, position: Int) {
        with(holder.binding){
            ivImage.load(String.format(
                root.context.getString(R.string.placeholder_thumbs),
                data[position].key
            )) {
                crossfade(true)
                placeholder(R.drawable.placeholder_landscape)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitList(data : List<DataTrailer>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data : MutableList<DataTrailer> by lazy {
        ArrayList()
    }
}
package com.oratakashi.movieq.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.movieq.data.model.genre.DataGenre
import com.oratakashi.movieq.databinding.AdapterGenreSmallBinding
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.viewBinding

class GenreAdapter : RecyclerView.Adapter<ViewHolder<AdapterGenreSmallBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterGenreSmallBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterGenreSmallBinding>, position: Int) {
        with(holder.binding){
            tvText.text = data[position].name
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitList(data : List<DataGenre>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data : MutableList<DataGenre> by lazy {
        ArrayList()
    }
}
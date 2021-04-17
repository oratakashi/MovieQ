package com.oratakashi.movieq.ui.genre

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.movieq.data.model.genre.DataGenre
import com.oratakashi.movieq.databinding.AdapterGenreBinding
import com.oratakashi.movieq.utils.Generator
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.viewBinding

class GenreAdapter(private val onclick : (DataGenre) -> Unit) : RecyclerView.Adapter<ViewHolder<AdapterGenreBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterGenreBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterGenreBinding>, position: Int) {
        with(holder.binding){
            vBackground.setBackgroundColor(Generator.generateColor(root.context))
            tvText.text = data[position].name
            root.onClick { onclick.invoke(data[position]) }
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
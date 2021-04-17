package com.oratakashi.movieq.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oratakashi.movieq.BuildConfig
import com.oratakashi.movieq.R
import com.oratakashi.movieq.data.model.cast.DataCast
import com.oratakashi.movieq.databinding.AdapterCastBinding
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.viewBinding

class CastAdapter : RecyclerView.Adapter<ViewHolder<AdapterCastBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterCastBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterCastBinding>, position: Int) {
        with(holder.binding){
            tvTitle.text = data[position].name
            tvSubtitle.text = data[position].character
            ivImage.load(BuildConfig.IMAGE_URL + data[position].profile_path){
                crossfade(true)
                placeholder(R.drawable.placeholder_portrait)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitList(data : List<DataCast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data : MutableList<DataCast> by lazy {
        ArrayList()
    }
}
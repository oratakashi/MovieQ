package com.oratakashi.movieq.ui.main

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.oratakashi.movieq.BuildConfig
import com.oratakashi.movieq.R
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.databinding.AdapterDiscoverBinding
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.viewBinding

class DiscoverAdapter (
    private val onClick : (DataDiscover) -> Unit
) : PagedListAdapter<DataDiscover, ViewHolder<AdapterDiscoverBinding>>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterDiscoverBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterDiscoverBinding>, position: Int) {
        with(holder.binding){
            if(getItem(position) != null){
                tvTitle.text = getItem(position)!!.title
                ivImage.load(BuildConfig.IMAGE_URL + getItem(position)?.poster_path){
                    placeholder(R.drawable.placeholder_portrait)
                }
                root.onClick { onClick.invoke(getItem(position)!!) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<DataDiscover>(){
            override fun areItemsTheSame(oldItem: DataDiscover, newItem: DataDiscover): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataDiscover, newItem: DataDiscover): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
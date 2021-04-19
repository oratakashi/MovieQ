package com.oratakashi.movieq.ui.review

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.CircleCropTransformation
import com.oratakashi.movieq.BuildConfig
import com.oratakashi.movieq.R
import com.oratakashi.movieq.data.model.review.DataReview
import com.oratakashi.movieq.databinding.AdapterReviewBinding
import com.oratakashi.movieq.utils.dateFormat
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.viewBinding
import com.squareup.picasso.Picasso

class ReviewAdapter : PagedListAdapter<DataReview, ViewHolder<AdapterReviewBinding>>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterReviewBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterReviewBinding>, position: Int) {
        with(holder.binding){
            if(getItem(position)?.author_details?.avatar_path != null){
                ivImage.load(when(
                    getItem(position)?.author_details?.avatar_path!!.contains("http") ||
                            getItem(position)?.author_details?.avatar_path!!.contains("https")
                ){
                    false   -> {
                        BuildConfig.IMAGE_URL + getItem(position)?.author_details?.avatar_path
                    }
                    true    -> {
                        getItem(position)?.author_details?.avatar_path!!.substring(1)
                    }
                }){
                    transformations(CircleCropTransformation())
                }
            }
            tvName.text = getItem(position)?.author_details?.username
            tvDate.dateFormat(
                getItem(position)?.updated_at!!
                    .replace("T", " ")
                    .replace("Z", ""),
                "yyyy-MM-dd HH:mm:ss",
                "dd MMMM yyyy HH:mm"
            )
            tvContent.text = getItem(position)?.content
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataReview>(){
            override fun areItemsTheSame(oldItem: DataReview, newItem: DataReview): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataReview, newItem: DataReview): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
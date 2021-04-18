package com.oratakashi.movieq.ui.detail

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.oratakashi.movieq.BuildConfig
import com.oratakashi.movieq.R
import com.oratakashi.movieq.data.model.review.DataReview
import com.oratakashi.movieq.databinding.AdapterReviewBinding
import com.oratakashi.movieq.utils.dateFormat
import com.oratakashi.viewbinding.core.*
import com.squareup.picasso.Picasso

class ReviewAdapter : RecyclerView.Adapter<ViewHolder<AdapterReviewBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterReviewBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterReviewBinding>, position: Int) {
        with(holder.binding){
            if(data[position].author_details?.avatar_path != null){
                Picasso.get()
                    .load(
                        when(
                            data[position].author_details?.avatar_path!!.contains("http") ||
                                    data[position].author_details?.avatar_path!!.contains("https")
                        ){
                            false   -> {
                                BuildConfig.IMAGE_URL + data[position].author_details?.avatar_path
                            }
                            true    -> {
                                data[position].author_details?.avatar_path!!.substring(1)
                            }
                        }
                    )
                    .placeholder(R.drawable.placeholder_portrait)
                    .error(R.drawable.placeholder_portrait)
                    .into(ivImage)
            }
            tvName.text = data[position].author_details?.username
            tvDate.dateFormat(
                data[position].updated_at!!
                    .replace("T", " ")
                    .replace("Z", ""),
                "yyyy-MM-dd HH:mm:ss",
                "dd MMMM yyyy HH:mm"
            )
            tvContent.text = data[position].content
        }
    }

    override fun getItemCount(): Int {
        return when(data.size > 3){
            true -> 3
            false -> data.size
        }
    }

    fun submitList(data : List<DataReview>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data : MutableList<DataReview> by lazy {
        ArrayList()
    }
}
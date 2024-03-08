package com.anirudh.spotifysearch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anirudh.spotifysearch.R
import com.anirudh.spotifysearch.data.model.AlbumItem
import com.anirudh.spotifysearch.data.model.ArtistInfo
import com.anirudh.spotifysearch.data.model.ItemInfo
import com.anirudh.spotifysearch.data.model.PlaylistItem
import com.anirudh.spotifysearch.data.model.TrackItem
import com.anirudh.spotifysearch.databinding.SearchResultItemBinding
import com.bumptech.glide.Glide

class SearchResultsAdapter(val onItemClick: (ItemInfo) -> Unit) :
    RecyclerView.Adapter<SearchResultsAdapter.ResultsViewHolder>() {


    private var resultsList: ArrayList<ItemInfo> = arrayListOf()

    fun updateList(info: List<ItemInfo>) {
        resultsList = info as ArrayList<ItemInfo>
        notifyDataSetChanged()
    }


    inner class ResultsViewHolder(val binding: SearchResultItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        return ResultsViewHolder(
            SearchResultItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return resultsList.size
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val item: ItemInfo = resultsList[position]
        holder.binding.apply {
            when (item) {
                is AlbumItem -> {
                    (item).apply {
                        Glide.with(holder.itemView.context).load(item.images[0].url).into(iv)
                        tvName.text = item.name
                        tvType.text = item.type
                    }
                }

                is TrackItem -> {
                    (item).apply {
                        iv.setImageResource(R.drawable.baseline_audiotrack_24)
                        tvName.text = item.name
                        tvType.text = item.type
                    }
                }

                is PlaylistItem -> {
                    (item).apply {
                        Glide.with(holder.itemView.context).load(item.images[0].url).into(iv)
                        tvName.text = item.name
                        tvType.text = item.type
                    }
                }

                is ArtistInfo -> {
                    (item).apply {
                        Glide.with(holder.itemView.context).load(item.images[0].url).into(iv)
                        tvName.text = item.name
                        tvType.text = item.type
                    }
                }
            }
        }


        holder.binding.apply {
            root.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }
}
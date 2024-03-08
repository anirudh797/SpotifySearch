package com.anirudh.spotifysearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.anirudh.spotifysearch.data.model.AlbumItem
import com.anirudh.spotifysearch.data.model.ArtistInfo
import com.anirudh.spotifysearch.data.model.ItemInfo
import com.anirudh.spotifysearch.data.model.PlaylistItem
import com.anirudh.spotifysearch.data.model.TrackItem
import com.anirudh.spotifysearch.databinding.ActivityDetailBinding
import com.anirudh.spotifysearch.util.Constants
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    lateinit var itemInfo: ItemInfo
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getSerializableExtra(Constants.ITEM_INFO)?.apply {
            itemInfo = this as ItemInfo
        }
        bindData()
    }

    private fun bindData() {
        binding.apply {
            val item = itemInfo
            when (itemInfo) {
                is AlbumItem -> {
                    (item as AlbumItem).apply {
                        Glide.with(this@DetailActivity).load(images[0].url).into(iv)
                        tvId.text = id
                        tvName.text = name
                        tvType.text = type
                    }
                }

                is TrackItem -> {
                    (item as TrackItem).apply {
                        iv.setImageResource(R.drawable.baseline_audiotrack_24)
                        tvId.text = id
                        tvName.text = item.name
                        tvType.text = item.type
                    }
                }

                is PlaylistItem -> {
                    (item as PlaylistItem).apply {
                        Glide.with(this@DetailActivity).load(item.images[0].url).into(iv)
                        tvId.text = id
                        tvName.text = item.name
                        tvType.text = item.type
                    }
                }

                is ArtistInfo -> {
                    (item as ArtistInfo).apply {
                        Glide.with(this@DetailActivity).load(item.images[0].url).into(iv)
                        tvId.text = id
                        tvName.text = item.name
                        tvType.text = item.type
                    }
                }
            }
        }
    }

}
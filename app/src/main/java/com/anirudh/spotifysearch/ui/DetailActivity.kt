package com.anirudh.spotifysearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.anirudh.spotifysearch.R
import com.anirudh.spotifysearch.data.model.AlbumItem
import com.anirudh.spotifysearch.data.model.ArtistInfo
import com.anirudh.spotifysearch.data.model.ItemInfo
import com.anirudh.spotifysearch.data.model.PlaylistItem
import com.anirudh.spotifysearch.data.model.TrackItem
import com.anirudh.spotifysearch.databinding.ActivityDetailBinding
import com.anirudh.spotifysearch.util.Constants
import com.anirudh.spotifysearch.viewModel.SearchViewModel
import com.bumptech.glide.Glide
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    lateinit var itemInfo: ItemInfo
    lateinit var binding: ActivityDetailBinding


    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getSerializableExtra(Constants.ITEM_INFO)?.apply {
            itemInfo = this as ItemInfo
        }
        fetchData()
        setupObservers()

    }

    private fun setupObservers() {
        viewModel.albumDetail.observe(this) {
            binding.apply {
                it.images.getOrNull(0)?.let {
                    Glide.with(this@DetailActivity).load(it.url).into(iv)
                }
                tvId.text = it.id
                tvName.text = it.name
                tvType.text = it.type
            }
        }

        viewModel.artistsInfo.observe(this) {
            binding.apply {
                it.images.getOrNull(0)?.let {
                    Glide.with(this@DetailActivity).load(it.url).into(iv)
                }
                tvId.text = it.id
                tvName.text = it.name
                tvType.text = it.type
            }
        }

        viewModel.playlistDetail.observe(this) {
            binding.apply {
                it.images.getOrNull(0)?.let {
                    Glide.with(this@DetailActivity).load(it.url).into(iv)
                }
                tvId.text = it.id
                tvName.text = it.name
                tvType.text = it.type
            }
        }

        viewModel.trackDetail.observe(this) {
            binding.apply {
                iv.setImageResource(R.drawable.baseline_audiotrack_24)
                tvId.text = it.id
                tvName.text = it.name
                tvType.text = it.type
            }
        }
    }

    private fun fetchData() {
        binding.apply {
            val item = itemInfo
            when (itemInfo) {
                is AlbumItem -> {
                    (item as AlbumItem).apply {
                        viewModel.getAlbumDetails(item.id)
                    }
                }

                is TrackItem -> {
                    (item as TrackItem).apply {
                        viewModel.getTrackDetails(id)
                    }

                }

                is PlaylistItem -> {
                    (item as PlaylistItem).apply {
                        viewModel.getPlaylistDetails(item.id)
                    }
                }

                is ArtistInfo -> {
                    (item as ArtistInfo).apply {
                        viewModel.getArtistDetails(item.id)
                    }
                }
            }
        }
    }

}
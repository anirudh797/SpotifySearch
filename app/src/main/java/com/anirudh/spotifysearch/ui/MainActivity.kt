package com.anirudh.spotifysearch.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
import com.anirudh.spotifysearch.R
import com.anirudh.spotifysearch.data.model.CategoryType
import com.anirudh.spotifysearch.databinding.ActivityMainBinding
import com.anirudh.spotifysearch.ui.adapters.CategoryAdapter
import com.anirudh.spotifysearch.viewModel.SearchViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.search)
        updateApiToken()
        setupClickListeners()
        setupObservers()
        viewModel.initializeDb(this)

    }

    private fun setupClickListeners() {
        binding.btn.setOnClickListener {
            viewModel.showLastSearchResults()
        }
    }

    private fun setupObservers() {
        viewModel.categories.observe(this) {
            setPager(categories = it)
        }
        viewModel.loadingProgressLiveData.observe(this) {
            if (it) {
                binding.loader.visibility = View.VISIBLE
                binding.tvSearchSomething.visibility = View.GONE
                binding.rootResults.visibility = View.GONE
            } else {
                binding.loader.visibility = View.GONE
                binding.rootResults.visibility = View.VISIBLE
            }
        }
    }

    private fun updateApiToken() {
        viewModel.updateApiAccessToken()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate menu with items using MenuInflator
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        Log.d("Anirudh", "setHasOptionsMenu ")

        // Initialise menu item search bar
        // with id and take its object
        val searchViewItem = menu.findItem(R.id.search_bar)
        val searchView: SearchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.queryHint = resources.getString(R.string.query_hint)

        // attach setOnQueryTextListener
        // to search view defined above
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //  onQueryTextSubmit method which is called when submit query is searched
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) {
                    return true
                }
                viewModel.lastQuery = query
                viewModel.getSearchResults(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setPager(categories: List<CategoryType>) {
        categories.ifEmpty { return }
        binding.pager.adapter = CategoryAdapter(categories, viewModel, this)
        binding.pager.offscreenPageLimit = OFFSCREEN_PAGE_LIMIT_DEFAULT
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            if (position in categories.indices) {
                val category = categories[position]
                category.let {
                    tab.text = it.key
                }
            }
        }.attach()
        binding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
        binding.pager.isUserInputEnabled = false
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector

    }


}

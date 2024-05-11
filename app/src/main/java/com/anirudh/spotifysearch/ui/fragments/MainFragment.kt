package com.anirudh.spotifysearch.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.anirudh.spotifysearch.R
import com.anirudh.spotifysearch.data.model.CategoryType
import com.anirudh.spotifysearch.databinding.FragmentMainBinding
import com.anirudh.spotifysearch.databinding.FragmentSearchResultsBinding
import com.anirudh.spotifysearch.ui.adapters.CategoryAdapter
import com.anirudh.spotifysearch.viewModel.SearchViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var searchViewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel =
            activity?.viewModelStore?.let { ViewModelProvider(it, searchViewModelFactory) }
                ?.get(SearchViewModel::class.java) ?: ViewModelProvider(
                this,
                searchViewModelFactory
            )[SearchViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                Log.d("Anirudh -----> ","onCreateOptionsMenu ")
                val inflater = menuInflater
                inflater.inflate(R.menu.menu, menu)
                Log.d("Anirudh", "setHasOptionsMenu ")
                // Initialise menu item search bar
                // with id and take its object
                val searchViewItem = menu.findItem(R.id.search_bar)
                setSearchView(searchViewItem)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return menuItem.itemId == R.menu.menu
            }

        })
        setToolbar(requireActivity())

    }

    private fun setToolbar(hostActivity: FragmentActivity) {
        Log.d("Anirudh -----> ","setToolbar ")
        if (hostActivity is AppCompatActivity) {
            hostActivity.apply {
                setSupportActionBar(binding.toolbar)
                supportActionBar?.title = resources.getString(R.string.search)
//                supportActionBar?.setDisplayShowTitleEnabled(false)
            }
        }

    }

    fun setSearchView(menuItem: MenuItem){
        val searchView: SearchView = MenuItemCompat.getActionView(menuItem) as SearchView
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
    }


    private fun setupObservers() {
        viewModel.categories.observe(viewLifecycleOwner) {
            setPager(categories = it)
        }
        viewModel.loadingProgressLiveData.observe(viewLifecycleOwner) {
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

    private fun setPager(categories: List<CategoryType>) {
        categories.ifEmpty { return }
        Log.d("Anirudh -----> ","setPager ")
        binding.pager.adapter = CategoryAdapter(categories, viewModel, this@MainFragment, null)
        binding.pager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
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



    companion object {
        @JvmStatic
        fun newInstance(param1: String? = null, param2: String? = null) =
            MainFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
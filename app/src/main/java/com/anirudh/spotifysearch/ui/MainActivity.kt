package com.anirudh.spotifysearch.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
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
import com.anirudh.spotifysearch.ui.fragments.MainFragment
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
        val fragment : MainFragment = MainFragment.newInstance()

        // for passing data to fragment
        val bundle = Bundle()
        fragment.arguments = bundle

        // check is important to prevent activity from attaching the fragment if already its attached
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fcv, fragment, "main_fragment")
                .commit()
        }
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.title = resources.getString(R.string.search)
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

    }

    private fun updateApiToken() {
        viewModel.updateApiAccessToken()
    }


    private fun setPager(categories: List<CategoryType>) {
        categories.ifEmpty { return }
//        binding.pager.adapter = CategoryAdapter(categories, viewModel, this)
//        binding.pager.offscreenPageLimit = OFFSCREEN_PAGE_LIMIT_DEFAULT
//        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
//            if (position in categories.indices) {
//                val category = categories[position]
//                category.let {
//                    tab.text = it.key
//                }
//            }
//        }.attach()
//        binding.tabLayout.addOnTabSelectedListener(object :
//            TabLayout.OnTabSelectedListener {
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//            }
//        })
//        binding.pager.isUserInputEnabled = false
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector

    }


}

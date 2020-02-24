package com.basim.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.basim.kotlinapp.data.model.Category
import com.basim.kotlinapp.databinding.ActivityMainBinding
import com.basim.kotlinapp.ui.MainViewModel
import com.basim.kotlinapp.ui.about.AboutFragment
import com.basim.kotlinapp.ui.gallery.GalleryFragment
import com.basim.kotlinapp.utils.Constants.Companion.FRAGMENT_ABOUT_KEY
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation = 0f
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        binding.viewModel = viewModel
        fab.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_first, AboutFragment()).addToBackStack(FRAGMENT_ABOUT_KEY).commit()
        }
        supportFragmentManager.beginTransaction().replace(R.id.container_second, GalleryFragment.newInstance(
            Category(getString(R.string.category_hot_label).toLowerCase())
        )).commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_hot -> {
                supportFragmentManager.beginTransaction().replace(R.id.container_second, GalleryFragment.newInstance(
                    Category(getString(R.string.category_hot_label).toLowerCase())
                )).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_top -> {
                supportFragmentManager.beginTransaction().replace(R.id.container_second, GalleryFragment.newInstance(
                    Category(getString(R.string.category_top_label).toLowerCase())
                )).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}

package org.school.demoapp.ui.homeScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_wallpapers_home_screen.*
import org.school.demoapp.AppConstant
import org.school.demoapp.AppConstant.category
import org.school.demoapp.R
import org.school.demoapp.databinding.ActivityWallpapersHomeScreenBinding
import org.school.demoapp.ui.categoryWallpaper.CategoryWallpaper
import org.school.demoapp.ui.homeScreen.adapter.CategoryAdapter
import org.school.demoapp.ui.homeScreen.clickListener.CategoryOnClick
import org.school.demoapp.ui.splash.MainViewModel

class WallpapersHomeScreen : AppCompatActivity(), CategoryOnClick, AppConstant
{
    private lateinit var wallpapersHomeScreen: ActivityWallpapersHomeScreenBinding
    private lateinit var categoryAdapter : CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wallpapersHomeScreen = DataBindingUtil.setContentView(this,R.layout.activity_wallpapers_home_screen)
        categoryAdapter = CategoryAdapter(this, this)
        wallpapersHomeScreen.rvCategory.adapter = categoryAdapter
        categoryAdapter.notifyDataSetChanged()
    }

    override fun onClick(categoryName: String)
    {
        startActivity(Intent(this@WallpapersHomeScreen, CategoryWallpaper::class.java)
            .putExtra(category, categoryName))
    }
}
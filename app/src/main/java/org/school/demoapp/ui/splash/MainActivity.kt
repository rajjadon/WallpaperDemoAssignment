package org.school.demoapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import org.school.demoapp.R
import org.school.demoapp.data.WallPapers
import org.school.demoapp.databinding.ActivityMainBinding
import org.school.demoapp.ui.homeScreen.WallpapersHomeScreen

class MainActivity : AppCompatActivity()
{
    private lateinit var activityMainBinding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setHandler()

        if ( ::mainViewModel.isInitialized )

            mainViewModel.wallpapers.observe(this, Observer {

                it?.hits?.get(1)?.largeImageURL?.let { image ->

                    Picasso.get().load(image).into(activityMainBinding.image)

                }
            })
    }

    private fun setHandler()
    {
       val handler = Handler()
        val r = Runnable {
            startActivity(Intent(this@MainActivity, WallpapersHomeScreen::class.java))
        }
        handler.postDelayed(r, 3000)
    }
}
package org.school.demoapp.ui.splash

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.EventLogTags
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.school.demoapp.R
import org.school.demoapp.databinding.ActivityMainBinding
import org.school.demoapp.ui.homeScreen.WallpapersHomeScreen
import java.util.*


class MainActivity : AppCompatActivity()
{
    private lateinit var activityMainBinding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if ( isOnline() )
        {
            mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

            setHandler()

            if ( ::mainViewModel.isInitialized )
            {
                val random = Random()
                val randomIndex: Int = random.nextInt(10) + 1
                mainViewModel.wallpapers.observe(this, Observer {

                    it?.wallPaperLists?.get(randomIndex)?.largeImageURL?.let { image ->

                        activityMainBinding.imageUrl = image
                        //Picasso.get().load(image).into(activityMainBinding.image)

                    }
                })
            }
        }
        else
        {
            activityMainBinding.includeNoData.tvNoData.text = "No internet connection "
            activityMainBinding.includeNoData.noData.visibility = View.VISIBLE
        }
    }

    private fun setHandler()
    {
       val handler = Handler()
        val r = Runnable {
            startActivity(Intent(this@MainActivity, WallpapersHomeScreen::class.java))
            finish()
        }
        handler.postDelayed(r, 3000)
    }

    fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        if (netInfo != null && netInfo.isConnectedOrConnecting) {
            return true
        }
        return false
    }


}
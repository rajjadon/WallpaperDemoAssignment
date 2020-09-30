package org.school.demoapp.ui.categoryWallpaper

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.school.demoapp.AppConstant
import org.school.demoapp.AppConstant.STORAGE_REQUEST
import org.school.demoapp.AppConstant.category
import org.school.demoapp.Downloader
import org.school.demoapp.R
import org.school.demoapp.data.local.DataBaseHandler
import org.school.demoapp.data.network.model.WallPaperList
import org.school.demoapp.databinding.ActivityCategoryWallpaperBinding
import org.school.demoapp.ui.categoryWallpaper.adapter.CategoryWallpaperAdapter
import org.school.demoapp.ui.categoryWallpaper.clicklistener.OnFav
import org.school.demoapp.ui.homeScreen.HomeScreenViewModel
import java.util.*

class CategoryWallpaper : AppCompatActivity(), AppConstant, OnFav
{
    private lateinit var categoryBinding : ActivityCategoryWallpaperBinding
    private lateinit var viewModel : HomeScreenViewModel
    private val downloader by lazy { Downloader() }
    private val categoryWallpaperAdapter by lazy { CategoryWallpaperAdapter(this@CategoryWallpaper) }
    private var categoryName : String? = null
    private lateinit var downloadUrl : String
    private lateinit var db : DataBaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_wallpaper)
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)

        if ( !::db.isInitialized )
            db = DataBaseHandler(this@CategoryWallpaper )

        init()
        setEvent()
    }

    private fun init()
    {
        categoryName = intent.getStringExtra(category)

        categoryName?.let { getCategoryWallpaper(it) }
    }

    private fun setEvent()
    {
        categoryBinding.svCategory.setOnRefreshListener { categoryName?.let { getCategoryWallpaper(
            it
        ) } }
    }

    private fun getCategoryWallpaper(categoryName: String)
    {
        categoryBinding.svCategory.isRefreshing = true
        viewModel.getCategoryWallpaper(categoryName).observe(
            this@CategoryWallpaper,
            Observer { wallpapers ->

                wallpapers?.let { hits ->

                    hits.wallPaperLists.let { hitList ->

                        categoryBinding.svCategory.isRefreshing = false
                        categoryWallpaperAdapter.wallpaperList = hitList
                        categoryBinding.rvCategory.adapter = categoryWallpaperAdapter
                    }
                }
            })
    }

    override fun onFavClick(wallPaperList: WallPaperList)
    {
        if ( ::db.isInitialized )
        {
            if ( wallPaperList.isFav )

                db.insertData( wallPaperList )

            else

                db.deleteData( wallPaperList.userId )
        }
    }

    override fun onDownloadClick(wallPaperList : WallPaperList) {

        wallPaperList.largeImageURL.let {

            downloadUrl = wallPaperList.largeImageURL
            checkPermissionForStorage( )
        }

    }

    private fun checkPermissionForStorage( ) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED )

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, ), STORAGE_REQUEST)

        else
            download( downloadUrl )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == STORAGE_REQUEST)

            if ( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED )

                download( downloadUrl )

    }

    private fun download( downloadUrl : String)
    {
        val filename = UUID.randomUUID().toString()+ ".jpg"

        Toast.makeText( this@CategoryWallpaper, "Download start", Toast.LENGTH_LONG).show()

        downloader.downloaderDm( this@CategoryWallpaper, filename)
        downloader.downloadManager( downloadUrl )
        downloader.isDownloaded.observe( this@CategoryWallpaper, Observer {

            if ( it )
                Toast.makeText( this@CategoryWallpaper, "Successfully download", Toast.LENGTH_LONG).show()

        })
    }
}
package org.school.demoapp.ui.favoriteList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import org.school.demoapp.R
import org.school.demoapp.data.local.DataBaseHandler
import org.school.demoapp.data.network.model.FavImageList
import org.school.demoapp.databinding.ActivityFavoriteImageListBinding
import org.school.demoapp.ui.favoriteList.adapter.FavoriteAdapter
import org.school.demoapp.ui.favoriteList.onClickListener.onUnFav

class FavoriteImageList : AppCompatActivity(), onUnFav
{
    private lateinit var favoriteImageBinding : ActivityFavoriteImageListBinding
    private lateinit var db : DataBaseHandler
    private val fabImageAdapter by lazy { FavoriteAdapter( this@FavoriteImageList ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteImageBinding = DataBindingUtil.setContentView(this@FavoriteImageList, R.layout.activity_favorite_image_list)

        if ( !::db.isInitialized )
            db = DataBaseHandler(this@FavoriteImageList )


        if ( ::db.isInitialized )
        {
            val favList = db.readData()

            if ( favList.isNotEmpty() )
            {
                fabImageAdapter.favoriteList = favList
                favoriteImageBinding.rvFavorite.adapter = fabImageAdapter
            }
            else
                favoriteImageBinding.includeNoData.noData.visibility = View.VISIBLE
        }
    }

    override fun onUnfavClick(fabImage: FavImageList) {

        if ( ::db.isInitialized )
        {
            db.deleteData( fabImage.name)
        }

    }
}
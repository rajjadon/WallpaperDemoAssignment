package org.school.demoapp.ui.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.school.demoapp.data.WallPapers
import org.school.demoapp.network.MyApi

class HomeScreenViewModel : ViewModel()
{
    val wallpapers : LiveData<WallPapers?> = MutableLiveData()

    fun getCategoryWallpaper(categoryName : String) : LiveData<WallPapers?>
    {
        viewModelScope.launch {
            wallpapers as MutableLiveData
            wallpapers.value = getWallpapers( categoryName )
        }

        return wallpapers
    }

    private suspend fun getWallpapers( categoryName : String ): WallPapers? {
        return withContext(Dispatchers.IO) {
            MyApi().getCategoryWallpapersList( categoryName ).body()
        }
    }
}
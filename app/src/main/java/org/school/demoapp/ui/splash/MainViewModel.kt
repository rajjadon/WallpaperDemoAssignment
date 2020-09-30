package org.school.demoapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.school.demoapp.data.network.model.WallPapers
import org.school.demoapp.data.network.MyApi

class MainViewModel : ViewModel()
{
    val wallpapers : LiveData<WallPapers?> = MutableLiveData()

    init {

        viewModelScope.launch {
            wallpapers as MutableLiveData
            wallpapers.value = getWallpapers()
        }
    }

    private suspend fun getWallpapers(): WallPapers? {
        return withContext(Dispatchers.IO) {
            MyApi().getWallpapersList().body()
        }
    }
}
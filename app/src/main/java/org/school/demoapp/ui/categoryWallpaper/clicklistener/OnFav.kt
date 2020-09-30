package org.school.demoapp.ui.categoryWallpaper.clicklistener

import org.school.demoapp.data.network.model.Hit

interface OnFav
{
    fun onFavClick(hit : Hit)
    fun onDownloadClick( hit : Hit)
}
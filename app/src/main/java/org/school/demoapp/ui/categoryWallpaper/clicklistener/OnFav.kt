package org.school.demoapp.ui.categoryWallpaper.clicklistener

import org.school.demoapp.data.network.model.WallPaperList

interface OnFav
{
    fun onFavClick(wallPaperList : WallPaperList)
    fun onDownloadClick(wallPaperList : WallPaperList)
}
package org.school.demoapp.ui.categoryWallpaper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.school.demoapp.R
import org.school.demoapp.data.network.model.WallPaperList
import org.school.demoapp.databinding.CategoryRowBinding
import org.school.demoapp.ui.categoryWallpaper.clicklistener.OnFav

class CategoryWallpaperAdapter(val action : OnFav) : RecyclerView.Adapter<CategoryWallpaperAdapter.CategoryViewHolder>()
{
    var wallpaperList : List<WallPaperList>? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder (
        DataBindingUtil.inflate( LayoutInflater.from( parent.context ), R.layout.category_row, parent, false )
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int)
    {
        var hit = wallpaperList?.get(position)


        wallpaperList?.get(position).let { holder.binding.wallpaper = hit }

        holder.binding.favUnFave.setOnClickListener {

            hit?.let {

                if ( it.isFav) {
                    it.isFav = false
                    holder.binding.favUnFave.setImageDrawable( ContextCompat.getDrawable( holder.binding.favUnFave.context, R.drawable.ic_baseline_favorite_border ) )
                    action.onFavClick( it )
                } else {
                    it.isFav = true
                    holder.binding.favUnFave.setImageDrawable( ContextCompat.getDrawable( holder.binding.favUnFave.context, R.drawable.ic_baseline_favorite ) )
                    action.onFavClick( it )
                }
            }
        }

        holder.binding.download.setOnClickListener {

            hit?.let {

                action.onDownloadClick( it )
            }
        }
    }

    override fun getItemCount() = wallpaperList?.size ?: 0

    inner class CategoryViewHolder(val binding: CategoryRowBinding) : RecyclerView.ViewHolder(binding.root)
}
package org.school.demoapp.ui.homeScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.school.demoapp.R
import org.school.demoapp.databinding.WallpaperCategoryRowBinding
import org.school.demoapp.ui.homeScreen.adapter.CategoryAdapter.WallpaperViewHolder
import org.school.demoapp.ui.homeScreen.clickListener.CategoryOnClick

class CategoryAdapter( context : Context, val action : CategoryOnClick) : RecyclerView.Adapter<WallpaperViewHolder>()
{
    val category = context.resources.getStringArray(R.array.wallpaperCategories)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = WallpaperViewHolder(

    DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.wallpaper_category_row, parent, false)

    )

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {

        val categoryName = category[position]

        holder.binding.categoryName = categoryName
        holder.itemView.setOnClickListener { action.onClick( categoryName ) }
    }

    override fun getItemCount() = category.size

    inner class WallpaperViewHolder(val binding: WallpaperCategoryRowBinding) : RecyclerView.ViewHolder(binding.root)
}
package org.school.demoapp.ui.favoriteList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.school.demoapp.R
import org.school.demoapp.data.network.model.FavImageList
import org.school.demoapp.data.network.model.WallPaperList
import org.school.demoapp.databinding.FavoriteRowBinding
import org.school.demoapp.ui.favoriteList.adapter.FavoriteAdapter.ViewHolder
import org.school.demoapp.ui.favoriteList.onClickListener.onUnFav

class FavoriteAdapter(val action : onUnFav) : RecyclerView.Adapter<ViewHolder>() {

    var favoriteList : MutableList<FavImageList>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ViewHolder(

        DataBindingUtil.inflate( LayoutInflater.from( parent.context ), R.layout.favorite_row, parent, false )

    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var fabvorite = favoriteList?.get(position)

        holder.binding.wallpaper = fabvorite

        holder.binding.favUnFave.setOnClickListener {

            fabvorite?.let {
                action.onUnfavClick( it )
                favoriteList?.removeAt(position)
                notifyDataSetChanged()
            }
        }
        
    }

    override fun getItemCount() = favoriteList?.size ?: 0

    inner class ViewHolder(val binding: FavoriteRowBinding) : RecyclerView.ViewHolder(binding.root)

}
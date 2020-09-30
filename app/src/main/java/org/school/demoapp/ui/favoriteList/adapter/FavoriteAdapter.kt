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

    var favoriteList : List<FavImageList>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ViewHolder(

        DataBindingUtil.inflate( LayoutInflater.from( parent.context ), R.layout.favorite_row, parent, false )

    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var fabvorite = favoriteList?.get(position)
        
        holder.binding.favUnFave.setOnClickListener {

            fabvorite?.let {

                holder.binding.favUnFave.setImageDrawable( ContextCompat.getDrawable( holder.binding.favUnFave.context, R.drawable.ic_baseline_favorite_border ) )
                action.onUnfavClick( it )
            }
        }
        
    }

    override fun getItemCount() = favoriteList?.size ?: 0

    inner class ViewHolder(val binding: FavoriteRowBinding) : RecyclerView.ViewHolder(binding.root)

}
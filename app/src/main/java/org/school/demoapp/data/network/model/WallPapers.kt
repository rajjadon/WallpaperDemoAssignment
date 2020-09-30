package org.school.demoapp.data.network.model


import com.google.gson.annotations.SerializedName

data class WallPapers(
    @SerializedName("hits")
    val wallPaperLists: List<WallPaperList>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
)
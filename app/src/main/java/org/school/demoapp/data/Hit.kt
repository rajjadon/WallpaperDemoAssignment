package org.school.demoapp.data


import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("comments")
    val comments: String,
    @SerializedName("downloads")
    val downloads: String,
    @SerializedName("favorites")
    val favorites: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageHeight")
    val imageHeight: String,
    @SerializedName("imageSize")
    val imageSize: String,
    @SerializedName("imageWidth")
    val imageWidth: String,
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @SerializedName("likes")
    val likes: String,
    @SerializedName("pageURL")
    val pageURL: String,
    @SerializedName("previewHeight")
    val previewHeight: String,
    @SerializedName("previewURL")
    val previewURL: String,
    @SerializedName("previewWidth")
    val previewWidth: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("userImageURL")
    val userImageURL: String,
    @SerializedName("views")
    val views: String,
    @SerializedName("webformatHeight")
    val webformatHeight: String,
    @SerializedName("webformatURL")
    val webformatURL: String,
    @SerializedName("webformatWidth")
    val webformatWidth: String,
    var isFav: Boolean = false
)
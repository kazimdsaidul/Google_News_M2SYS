package com.saidul.googlenews.data.network.model

import android.os.Parcelable
import androidx.versionedparcelable.NonParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    @SerializedName("author")
    var author: String? = null,

    @SerializedName("content")
    var content: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @SerializedName("title")
    var title: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("urlToImage")
    var urlToImage: String? = null
) : Parcelable {

    @SerializedName("source")
    @NonParcelField
    var source: Source? = null

}
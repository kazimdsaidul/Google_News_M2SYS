package com.saidul.googlenews.data.network.model

import com.google.gson.annotations.SerializedName

class ReposeHeadlines {
    @SerializedName("articles")
    var articles: List<Article>? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResults: Long? = null

}
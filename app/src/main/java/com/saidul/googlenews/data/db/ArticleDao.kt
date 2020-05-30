package com.saidul.googlenews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saidul.googlenews.data.network.model.Article

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-03.
 */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article")
    suspend fun getAllArticle(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleList(articles: List<Article>?)


    @Query("DELETE FROM Article")
    suspend fun clearArticleList()


}
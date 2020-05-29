package com.saidul.googlenews.ui.homePage.fragment.model

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.saidul.googlenews.R
import com.saidul.googlenews.data.network.model.Article
import com.saidul.googlenews.databinding.ItemHeallineBinding
import com.xwray.groupie.databinding.BindableItem


/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 5/29/20.
 */
class ArticleItem(private val im: Article, val onLikeClickedListener: OnLikeClickedListener) :
    BindableItem<ItemHeallineBinding>() {

    override fun getLayout() = R.layout.item_healline

    override fun bind(viewBinding: ItemHeallineBinding, position: Int) {
        viewBinding.article = im


        val url = im.urlToImage
        Glide.with(viewBinding.ivEmpImage.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .error(R.drawable.noimage_found)
            .into(viewBinding.ivEmpImage)



        viewBinding.ivEmpImage.setOnClickListener {
            onLikeClickedListener.onLikeClicked(im)
        }
    }

    interface OnLikeClickedListener {
        fun onLikeClicked(item: Article)
    }
}




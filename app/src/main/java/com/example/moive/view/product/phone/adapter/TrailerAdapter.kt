package com.example.moive.view.product.phone.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.example.moive.R
import com.example.moive.base.recycleview.BaseAdapter
import com.example.moive.databinding.ItemTrailerBinding
import com.example.moive.view.model.Product

class TrailerAdapter (
    val context: Context,
    var list: List<Product>,
    val callBack: ItemClickListener,
) : BaseAdapter<ItemTrailerBinding, Product>(list) {
    override val layoutId: Int = R.layout.item_trailer

    override fun bind(binbing: ItemTrailerBinding, item: Product, position: Int) {
        binbing.model = item
        Glide.with(context).load(item.thumbnail).into(binbing.ivMovie);

    }

    interface ItemClickListener {
        fun onTrendItemclicked( item: Product)
    }
}
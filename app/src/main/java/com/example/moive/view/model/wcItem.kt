package com.example.moive.view.model

import com.example.moive.base.recycleview.AdapterItem

data class wcItem(
    val `data`: List<DataItem>,
    val type: String
)

data class DataItem(
    val actualPrice: Int,
    val categoryImage: String,
    val description: String,
    val id: String,
    val image: String,
    val isExpress: Boolean,
    val name: String,
    val offerPercentage: Int,
    val offerPrice: Int
):AdapterItem
package com.example.moive.Utils

import android.icu.text.SimpleDateFormat
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.moive.base.recycleview.AdapterItem
import com.example.moive.base.recycleview.BaseAdapter
import com.example.moive.extensions.appendAsterisk
import java.util.Calendar

object BindingUtils {
    @BindingAdapter("setAdapter")
    @JvmStatic
    fun setAdapter(
        recyclerView: RecyclerView,
        adapter: BaseAdapter<ViewDataBinding, AdapterItem>?
    ) {
        adapter?.let {
            recyclerView.adapter = it
        }
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("submitList")
    fun submitList(recyclerView: RecyclerView, list: List<AdapterItem>?) {
        val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, AdapterItem>?
        adapter?.updateData(list ?: listOf())
    }







}
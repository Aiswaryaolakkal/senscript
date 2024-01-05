package com.example.moive.base.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<BINDING : ViewDataBinding, T : AdapterItem>(var data: List<T>) :
    RecyclerView.Adapter<BaseViewHolder<BINDING>>() {
    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binbing: BINDING, item: T, position: Int)

    fun updateData(list: List<T>) {
        this.data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {
        val binding = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context), layoutId, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        bind(holder.binder, data[position], position)
    }

    override fun getItemCount(): Int = data.size
}
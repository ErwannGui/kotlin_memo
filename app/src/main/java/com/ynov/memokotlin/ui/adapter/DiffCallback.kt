package com.ynov.memokotlin.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ynov.memokotlin.storage.Memo


class DiffCallback(
    private val oldList: List<Memo>,
    private val newList: List<Memo>
) : DiffUtil.Callback() {

    // Method #1
    override fun getOldListSize() = oldList.size

    // Method #2
    override fun getNewListSize() = newList.size

    // Method #3
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    // Method #4
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].content == newList[newItemPosition].content
    }
}
package com.ynov.memokotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.ynov.memokotlin.R
import com.ynov.memokotlin.storage.Memo
import kotlinx.android.synthetic.main.memo_items.view.*



class MemoAdapter(
    memoList: List<Memo>,
    private val interaction: Interaction? = null
) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    private val memos = mutableListOf<Memo>()

    init {
        memos.addAll(memoList)
    }

    // Method #1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.memo_items, parent, false)
        return ViewHolder(
            view,
            interaction
        )
    }

    // Method #2
    override fun getItemCount() = memos.size


    // Method #3
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = memos[position])
    }


    // Method #4
    fun swap(memos: List<Memo>) {
        val diffCallback = DiffCallback(this.memos, memos)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.memos.clear()
        this.memos.addAll(memos)
        diffResult.dispatchUpdatesTo(this)
    }


    // Method #5
    class ViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        // Method #6
        fun bind(item: Memo) {
            itemView.txtTitle.text = item.title
            itemView.txtContent.text = item.content

            //Handle item click
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition,item)
            }
        }

    }

    // Method #7
    interface Interaction {
        fun onItemSelected(position: Int, item: Memo)
    }
}
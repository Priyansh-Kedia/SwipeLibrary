package com.kedia.customswipelibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val context: Context,
    private val list: MutableList<String>
): RecyclerView.Adapter<Adapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ic_card, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun removeItem(adapterPosition: Int) {
        this.list.removeAt(adapterPosition)
    }

    fun moveItem(start: Int, end: Int) {
        val temp = list[start]
        list[start] = list[end]
        list[end] = temp
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var textView = itemView.findViewById<TextView>(R.id.textView)

        fun bind(string: String) {
            textView.text = string
        }
    }
}

package com.kedia.customswipelibrary

import android.content.Context
import android.util.Log
import android.view.GestureDetector
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

    fun moveItem(positionStart: Int, positionEnd: Int) {
        val temp = list[positionEnd]
        Log.d("TAG!!!!", temp)
        list[positionEnd] = list[positionStart]
        Log.d("TAG!!!!", list[positionEnd])
        list[positionStart] = temp
        Log.d("TAG!!!!", list[positionStart])
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var textView = itemView.findViewById<TextView>(R.id.textView)

        fun bind(string: String) {
            textView.text = string
        }
    }

}

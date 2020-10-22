package com.kedia.customswipelibrary

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

//fun RecyclerView.addSwipeToDelete() {
//    val simpleCallback = object : ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.RIGHT) {
//        override fun onMove(
//            recyclerView: RecyclerView,
//            viewHolder: RecyclerView.ViewHolder,
//            target: RecyclerView.ViewHolder
//        ): Boolean {
//            return false
//        }
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//
//        }
//
//    }
//
//    ItemTouchHelper(simpleCallback).attachToRecyclerView(this)
//}
package com.kedia.swipetodelete

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView

object SwipeToDelete  {

    fun RecyclerView.addSwipeToDelete(
        list: List<DIRECTION> = emptyList(),
        listener: OnSwiped? = null
    ) {

        var swipeDirs = RIGHT
        for (element in list) {
            if (element != DIRECTION.RIGHT) {
                swipeDirs = swipeDirs or DIRECTION.valueOf(element.name).direction
            }
        }

        val simpleCallback = object : ItemTouchHelper.SimpleCallback(0 , swipeDirs) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                this@addSwipeToDelete.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
                listener?.swipeToDelete(adapterPosition = viewHolder.adapterPosition)
            }

        }
        ItemTouchHelper(simpleCallback).attachToRecyclerView(this)
    }

    interface OnSwiped {
        fun swipeToDelete(adapterPosition: Int)
    }

    enum class DIRECTION(val direction: Int) {
        UP (ItemTouchHelper.UP),
        DOWN(ItemTouchHelper.DOWN),
        RIGHT(ItemTouchHelper.RIGHT),
        LEFT(ItemTouchHelper.LEFT)
    }


}
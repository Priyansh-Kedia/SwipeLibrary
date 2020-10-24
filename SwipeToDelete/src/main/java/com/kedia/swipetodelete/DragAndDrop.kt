package com.kedia.swipetodelete

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


private lateinit var gestureDetector: GestureDetector
private lateinit var touchHelper: ItemTouchHelper

private lateinit var holder: RecyclerView.ViewHolder


fun RecyclerView.addDragToShift(listener: OnDragged? = null) {

    val itemTouchCallback = object : ItemTouchHelper.Callback() {
        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                viewHolder!!.itemView.alpha = 0.2f
            }
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            viewHolder.itemView.alpha = 1f
        }

        override fun isItemViewSwipeEnabled(): Boolean {
            return false
        }

        override fun isLongPressDragEnabled(): Boolean {
            return false
        }

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            holder = viewHolder
            holder.itemView.setGestures()
            val drags =
                ItemTouchHelper.START or ItemTouchHelper.END or ItemTouchHelper.UP or ItemTouchHelper.DOWN
            return makeMovementFlags(
                drags,
                0
            )
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            listener?.onPositionDragged(viewHolder.adapterPosition, target.adapterPosition)
            adapter?.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    }


    val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
    itemTouchHelper.attachToRecyclerView(this)
    touchHelper = itemTouchHelper
}


fun View.setGestures() {
    gestureDetector = GestureDetector(this.context, object : GestureDetector.OnGestureListener {
        override fun onShowPress(p0: MotionEvent?) {

        }

        override fun onSingleTapUp(p0: MotionEvent?): Boolean {
            return false
        }

        override fun onDown(p0: MotionEvent?): Boolean {
            return false
        }

        override fun onFling(
            p0: MotionEvent?,
            p1: MotionEvent?,
            p2: Float,
            p3: Float
        ): Boolean {
            return false
        }

        override fun onScroll(
            p0: MotionEvent?,
            p1: MotionEvent?,
            p2: Float,
            p3: Float
        ): Boolean {
            return false
        }

        override fun onLongPress(p0: MotionEvent?) {
            touchHelper.startDrag(holder)
        }

    })
    this.setOnTouchListener { v, event ->
        gestureDetector.onTouchEvent(event)
        true
    }
}

interface OnDragged {
    fun onPositionDragged(fromPosition: Int, toPosition: Int)
}

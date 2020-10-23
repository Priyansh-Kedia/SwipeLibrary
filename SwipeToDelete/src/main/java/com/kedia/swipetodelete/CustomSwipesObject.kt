package com.kedia.swipetodelete

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

object CustomSwipesObject : View.OnTouchListener, GestureDetector.OnGestureListener {

    private lateinit var gestureDetector: GestureDetector
    private lateinit var touchHelper: ItemTouchHelper

    private lateinit var holder: ViewHolder

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

    fun RecyclerView.addDragToSwipe(): ItemTouchHelper {

        val itemTouchCallback = object : ItemTouchHelper.Callback() {
            override fun onSelectedChanged(viewHolder: ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState == ACTION_STATE_DRAG) {
                    viewHolder!!.itemView.alpha = 0.2f
                }
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: ViewHolder) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.alpha = 1f
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return false
            }

            override fun isLongPressDragEnabled(): Boolean {
                return false
            }

            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
                holder = viewHolder
                val drags =
                    START or END or UP or DOWN
                return makeMovementFlags(
                    drags,
                    0
                )
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                (adapter as ItemTouchHelperAdapter).onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {}

        }


        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(this)
        touchHelper = itemTouchHelper
        return itemTouchHelper
    }

    fun View.setGestures() {
        gestureDetector = GestureDetector(this.context, this@CustomSwipesObject)
        this.setOnTouchListener(this@CustomSwipesObject)
    }

    private fun onItemMove(
        fromPosition: Int,
        toPosition: Int,
        recyclerView: RecyclerView
    ) {
        recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
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

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent?) {}

    override fun onSingleTapUp(e: MotionEvent?): Boolean {

        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        touchHelper?.startDrag(holder)
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }

}
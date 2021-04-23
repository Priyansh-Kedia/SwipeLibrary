package com.kedia.swipetodelete

import android.graphics.Canvas
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kedia.swipetodelete.utils.DIRECTION
import java.lang.Exception

fun RecyclerView.addSwipeToPerform(
    list: List<DIRECTION>? = emptyList(),
    listener: OnSwipeToPerform? = null,
    @ColorInt colorOneInt: Int? = null,
    @ColorInt colorTwoInt: Int? = null
) {

    var swipeDirs = ItemTouchHelper.RIGHT
    list?.apply {
        for (element in list) {
            if (element != DIRECTION.RIGHT) {
                swipeDirs = swipeDirs or DIRECTION.valueOf(element.name).direction
            }
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
            val swipeDirection: DIRECTION = when(direction) {
                ItemTouchHelper.UP -> DIRECTION.UP
                ItemTouchHelper.DOWN -> DIRECTION.DOWN
                ItemTouchHelper.LEFT -> DIRECTION.LEFT
                else -> DIRECTION.RIGHT
            }
            listener?.swipeToPerform(adapterPosition = viewHolder.adapterPosition,swipeDirection = swipeDirection)
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            c.clipRect(0f, viewHolder.itemView.top.toFloat(),
                dX, viewHolder.itemView.bottom.toFloat())

            if (colorTwoInt != null && colorOneInt == null)
                throw Exception("Color One cannot be null if Color Two is non null")

            if (colorTwoInt == null) {
                colorOneInt?.apply { c.drawColor(this) }
            } else {
                if(dX < width / 2)
                    colorOneInt?.apply { c.drawColor(this) }
                else
                    colorTwoInt?.apply { c.drawColor(this) }
            }

            super.onChildDraw(
                c,
                recyclerView,
                viewHolder,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }
    ItemTouchHelper(simpleCallback).attachToRecyclerView(this)
}

interface OnSwipeToPerform {
    fun swipeToPerform(adapterPosition: Int, swipeDirection: DIRECTION)
}

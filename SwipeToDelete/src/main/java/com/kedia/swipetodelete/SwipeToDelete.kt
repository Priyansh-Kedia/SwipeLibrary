package com.kedia.swipetodelete

import android.R
import android.graphics.*
import android.util.Log
import android.view.View
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
//                listener?.swipeToDelete(adapterPosition = viewHolder.adapterPosition)
//                this@addSwipeToDelete.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
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
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && viewHolder.itemView.translationX.isPositive()) {
                    viewHolder.itemView.translationX = dX / 10
                    val itemView: View = viewHolder.itemView
                    val height =
                        itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 5
                    val paint = Paint()
                    paint.color = Color.parseColor("#000000")
                    val background = RectF(
                        itemView.left.toFloat() + dX / 5,
                        itemView.top.toFloat(),
                        itemView.left.toFloat(),
                        itemView.bottom.toFloat()
                    )
                    c.drawRect(background, paint)
                    val icon = BitmapFactory.decodeResource(resources, R.drawable.alert_light_frame)
                    val icon_dest = RectF(
                        (itemView.left.toFloat() + dX / 20),
                        itemView.top.toFloat() + width,
                        itemView.left.toFloat() + dX / 7,
                        itemView.bottom.toFloat() - width
                    )
                    c.drawBitmap(icon, null, icon_dest, paint)
                } else {
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && !viewHolder.itemView.translationX.isPositive() ) {
                        viewHolder.itemView.translationX = dX / 10
                        val itemView: View = viewHolder.itemView
                        val height =
                            itemView.bottom.toFloat() - itemView.top.toFloat()
                        val width = height / 5
                        val paint = Paint()
                        paint.color = Color.parseColor("#000000")
                        val background = RectF(
                            itemView.right.toFloat() + dX / 5,
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, paint)
                        val icon = BitmapFactory.decodeResource(resources, R.drawable.alert_light_frame)
                        val icon_dest = RectF(
                            (itemView.right.toFloat() + dX / 7),
                            itemView.top.toFloat() + width,
                            itemView.right.toFloat() + dX / 20,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, icon_dest, paint)

                    } else {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    }
                }
            }

        }
        ItemTouchHelper(simpleCallback).attachToRecyclerView(this)
    }

    private fun Float.isPositive(): Boolean {
        return this > 0
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
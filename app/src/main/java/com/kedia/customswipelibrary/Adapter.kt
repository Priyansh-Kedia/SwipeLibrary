package com.kedia.customswipelibrary

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kedia.swipetodelete.CustomSwipesObject.setGestures
import com.kedia.swipetodelete.ItemTouchHelperAdapter

class Adapter(
    private val context: Context,
    private val list: List<String>
): RecyclerView.Adapter<Adapter.CustomViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ic_card, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//        GestureDetector.OnGestureListener, View.OnTouchListener
                {

        private var textView = itemView.findViewById<TextView>(R.id.textView)
        private lateinit var gestureDetector: GestureDetector

        fun bind(string: String) {
            textView.text = string
            textView.setGestures()
//            gestureDetector = GestureDetector(itemView.context, this)
//            itemView.setOnTouchListener(this)
        }

//        override fun onDown(e: MotionEvent?): Boolean {
//            return false
//        }
//
//        override fun onShowPress(e: MotionEvent?) {}
//
//        override fun onSingleTapUp(e: MotionEvent?): Boolean {
//
//            return false
//        }
//
//        override fun onScroll(
//            e1: MotionEvent?,
//            e2: MotionEvent?,
//            distanceX: Float,
//            distanceY: Float
//        ): Boolean {
//            return false
//        }
//
//        override fun onLongPress(e: MotionEvent?) {
//            Log.d("TAG!!!!","called long")
//            touchHelper?.startDrag(this)
//        }
//
//        override fun onFling(
//            e1: MotionEvent?,
//            e2: MotionEvent?,
//            velocityX: Float,
//            velocityY: Float
//        ): Boolean {
//            return false
//        }

//        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//            gestureDetector.onTouchEvent(event)
//            return true
//        }

    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {

        notifyItemMoved(fromPosition, toPosition)
    }

}

package com.kedia.customswipelibrary

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kedia.swipetodelete.*
import com.kedia.swipetodelete.utils.DIRECTION
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnSwiped, OnDragged, OnSwipeToPerform {

    private val list = mutableListOf<String>()
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..29) {
            list.add("some text $i")
        }

        adapter = Adapter(this@MainActivity, list)
        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        recycler.addDragToShift(this)

        val list = listOf(
            DIRECTION.LEFT,
            DIRECTION.RIGHT)
        recycler.addSwipeToPerform(list, this, ContextCompat.getColor(this, R.color.colorPrimaryDark))
        recycler.addSwipeToDelete(list, this, ContextCompat.getColor(this, R.color.colorPrimaryDark))
//        try {
//            SwipeToDelete.javaClass.getDeclaredMethod("some").invoke(SwipeToDelete)
//        } catch (e: NoSuchMethodException) {
//            e.printStackTrace()
//        }
    }

    override fun onPositionDragged(fromPosition: Int, toPosition: Int) {
        adapter.moveItem(fromPosition, toPosition)
    }

    override fun swipeToDelete(adapterPosition: Int) {
        adapter.removeItem(adapterPosition)
    }

    override fun swipeToPerform(adapterPosition: Int) {
        Log.d("TAG!!!!", "swipeToPerform: swiped")
    }

}
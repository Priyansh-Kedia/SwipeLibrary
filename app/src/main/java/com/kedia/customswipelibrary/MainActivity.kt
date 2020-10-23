package com.kedia.customswipelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.kedia.swipetodelete.CustomSwipesObject
import com.kedia.swipetodelete.CustomSwipesObject.addDragToSwipe
import com.kedia.swipetodelete.CustomSwipesObject.addSwipeToDelete
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CustomSwipesObject.OnSwiped {

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

        recycler.addDragToSwipe()

        val list = listOf(CustomSwipesObject.DIRECTION.LEFT,
            CustomSwipesObject.DIRECTION.RIGHT)
        recycler.addSwipeToDelete(list, this)
    }

    override fun swipeToDelete(adapterPosition: Int) {
        Log.d("TAG!!!!", adapter.itemCount.toString())
    }
}
package com.kedia.customswipelibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kedia.swipetodelete.SwipeToDelete
import com.kedia.swipetodelete.SwipeToDelete.addSwipeToDelete
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeToDelete.OnSwiped {

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

        val list = listOf(SwipeToDelete.DIRECTION.LEFT,
            SwipeToDelete.DIRECTION.RIGHT)
        recycler.addSwipeToDelete(list, this)
    }

    override fun swipeToDelete(adapterPosition: Int) {

    }
}
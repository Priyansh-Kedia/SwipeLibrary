package com.kedia.customswipelibrary

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kedia.swipetodelete.DragAndDrop
import com.kedia.swipetodelete.DragAndDrop.addDragToSwipe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DragAndDrop.onDragged {

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
        recycler.addDragToSwipe(this)

    }

    override fun onPositionDragged(positionStart: Int, positionEnd: Int) {
        Log.d("TAG!!!!", "$positionEnd $positionStart")
    }

}
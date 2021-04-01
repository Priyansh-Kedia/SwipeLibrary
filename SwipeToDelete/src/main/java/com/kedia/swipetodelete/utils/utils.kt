package com.kedia.swipetodelete.utils

import androidx.recyclerview.widget.ItemTouchHelper

enum class DIRECTION(val direction: Int) {
    UP (ItemTouchHelper.UP),
    DOWN(ItemTouchHelper.DOWN),
    RIGHT(ItemTouchHelper.RIGHT),
    LEFT(ItemTouchHelper.LEFT)
}
package com.mateuszkukiel.core.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val spaceSize: Int,
    private val orientation: Int = LinearLayoutManager.VERTICAL
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            val itemCount = state.itemCount
            if (orientation == LinearLayoutManager.VERTICAL) {
                if (position != 0) {
                    bottom = spaceSize
                }
                if (position != itemCount - 1) {
                    top = spaceSize
                }
            } else {
                if (position != 0) {
                    left = spaceSize
                }
                if (position != itemCount - 1) {
                    right = spaceSize
                }
            }
        }
    }
}
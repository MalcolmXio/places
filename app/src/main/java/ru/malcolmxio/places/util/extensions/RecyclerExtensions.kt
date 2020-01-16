package ru.malcolmxio.places.util.extensions

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.malcolmxio.places.R

fun RecyclerView.addDividerItemDecoration() {
    this.apply {
        val mLayoutManager = layoutManager as? LinearLayoutManager
        mLayoutManager?.let {
            ContextCompat.getDrawable(context, R.drawable.common_recycler_item_divider)
                ?.let { drawable ->
                    val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
                    dividerItemDecoration.setDrawable(drawable)
                    addItemDecoration(dividerItemDecoration)
                }
        }
    }
}
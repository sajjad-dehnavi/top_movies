package dehnavi.sajjad.topmoview.utils

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dehnavi.sajjad.topmoview.ui.home.adaters.TopMoviesAdapter


fun RecyclerView.initRecycler(
    layoutManager: RecyclerView.LayoutManager,
    adapter: RecyclerView.Adapter<*>
) {
    this.layoutManager = layoutManager
    this.adapter = adapter
}

fun View.showGone(isShow: Boolean) {
    visibility = if (isShow)
        View.VISIBLE
    else
        View.GONE
}
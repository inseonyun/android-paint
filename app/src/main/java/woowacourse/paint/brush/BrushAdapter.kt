package woowacourse.paint.brush

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.paint.model.Brush

class BrushAdapter(
    private val items: List<Brush>,
    private val onBrushClick: (Brush) -> Unit,
) : RecyclerView.Adapter<BrushViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushViewHolder =
        BrushViewHolder(parent)

    override fun onBindViewHolder(holder: BrushViewHolder, position: Int) {
        holder.bind(items[position], onBrushClick)
    }

    override fun getItemCount(): Int = items.size
}

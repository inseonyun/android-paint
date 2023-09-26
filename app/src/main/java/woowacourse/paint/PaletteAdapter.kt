package woowacourse.paint

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PaletteAdapter(
    private val items: List<Int>,
    private val onPaletteClick: (Int) -> Unit,
) : RecyclerView.Adapter<PaletteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder {
        return PaletteViewHolder(parent, onPaletteClick)
    }

    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
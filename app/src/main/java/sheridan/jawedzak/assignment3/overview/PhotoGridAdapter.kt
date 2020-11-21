package sheridan.jawedzak.assignment3.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sheridan.jawedzak.assignment3.databinding.GridViewItemBinding
import sheridan.jawedzak.assignment3.network.FlowerJson

class PhotoGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<FlowerJson, PhotoGridAdapter.FlowersPropertyViewHolder>(DiffCallback) {

    class FlowersPropertyViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(flowersProperty: FlowerJson) {
            binding.property = flowersProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FlowerJson>() {
        override fun areItemsTheSame(oldItem: FlowerJson, newItem: FlowerJson): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FlowerJson, newItem: FlowerJson): Boolean {
            return oldItem.pictures == newItem.pictures
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): FlowersPropertyViewHolder {
        return FlowersPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FlowersPropertyViewHolder, position: Int) {
        val flowersProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(flowersProperty)
        }
        holder.bind(flowersProperty)
    }

    class OnClickListener(val clickListener: (flowersProperty:FlowerJson) -> Unit) {
        fun onClick(flowersProperty:FlowerJson) = clickListener(flowersProperty)
    }


}
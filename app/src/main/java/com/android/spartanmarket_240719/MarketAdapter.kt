package com.android.spartanmarket_240719

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.spartanmarket_240719.databinding.ItemLayoutBinding
import org.w3c.dom.Text

class MarketAdapter(val Pro_List: List<SpartanItem>):
    RecyclerView.Adapter<MarketAdapter.Container>() {

        var listClick:((Int) -> Unit)? = null

        inner class Container(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bindbind(item: SpartanItem) {
                binding.itemImageView.setImageResource(item.img_index)
                binding.itemTitleTextView.text = item.title
                binding.itemLocationTextView.text = item.location
                binding.itemPriceTextView.text = "${String.format("%,d", item.price)} 원"
                binding.itemCommentTextView.text = item.comment_n
                binding.itemHeartTextView.text = item.heart_n

                binding.root.setOnClickListener{
                    listClick?.invoke(adapterPosition)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketAdapter.Container {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Container(binding)
    }

    override fun onBindViewHolder(holder: MarketAdapter.Container, position: Int) {
        holder.bindbind(Pro_List[position])
    }

    override fun getItemCount(): Int {
        return Pro_List.size
    }
}
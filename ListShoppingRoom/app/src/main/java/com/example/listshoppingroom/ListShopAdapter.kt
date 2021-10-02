package com.example.listshoppingroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listshoppingroom.databinding.ItemListBinding
import com.example.listshoppingroom.entities.ListShop
import com.example.listshoppingroom.repository.ListShopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Suppress("MemberVisibilityCanBePrivate")
class ListShopAdapter(private val list: List<ListShop>) :
    RecyclerView.Adapter<ListShopAdapter.ListShopViewHolder>() {
    class ListShopViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListShopViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListShopViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ListShopViewHolder, position: Int) {
        with(holder.binding) {
            tvTitle.text = list[position].name
            tvPrice.text = "$"+list[position].price
            tvAmount.text = "x"+list[position].amount
            val repository = ListShopRepository.getRepository(holder.binding.root.context)
            tvDelete.setOnClickListener{
                CoroutineScope(Dispatchers.IO).launch {
                    repository.deleteOne(list[position].id)
                }
            }
        }

    }
    override fun getItemCount(): Int = list.size
}
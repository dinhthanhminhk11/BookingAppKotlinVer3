package com.example.bookingappkotlinver3.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingappkotlinver3.databinding.ItemBestforyouHomefragmentBinding
import com.example.bookingappkotlinver3.databinding.ItemNearfromyouHomefragmentBinding
import com.example.bookingappkotlinver3.model.Hotel

class BestForYouAdapter : RecyclerView.Adapter<BestForYouAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Hotel>() {
        override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestForYouAdapter.ViewHolder {
        val binding = ItemBestforyouHomefragmentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BestForYouAdapter.ViewHolder, position: Int) {
        val hotel: Hotel = differ.currentList[position]
        holder.bind(hotel)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(val binding: ItemBestforyouHomefragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hotel) {

        }
    }

    private var onItemClickListener: ((Hotel) -> Unit)? = null

    fun setOnItemClickListener(listener: (Hotel) -> Unit) {
        onItemClickListener = listener
    }
}
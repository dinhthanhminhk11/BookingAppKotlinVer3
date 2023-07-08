package com.example.bookingappkotlinver3.view.adapter

import android.content.ClipData
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bookingappkotlinver3.R
import com.example.bookingappkotlinver3.databinding.ItemNearfromyouHomefragmentBinding
import com.example.bookingappkotlinver3.model.Hotel
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.DecimalFormat

class NearFromYouAdapter : RecyclerView.Adapter<NearFromYouAdapter.ViewHolder>() {
    val df = DecimalFormat("0.0")

    private val callback = object : DiffUtil.ItemCallback<Hotel>() {
        override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNearfromyouHomefragmentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotel: Hotel = differ.currentList[position]
        holder.bind(hotel)
    }

    inner class ViewHolder(val binding: ItemNearfromyouHomefragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hotel) {
            val options =
                RequestOptions().centerCrop().placeholder(R.drawable.imageloading).error(R.drawable.imageerror)

            Glide.with(binding.imgItemNearFromYou.context).load(item.images[0]).apply(options)
                .into(binding.imgItemNearFromYou)

            binding.tvAddressItemNearFromYou.text =
                "${item.sonha}, ${item.xa}, ${item.huyen}, ${item.tinh}"
            binding.tvDistanceItemNearFromYou.text = "${df.format(item.calculated / 1000)} Km"
            binding.tvNameItemNearFromYou.text = item.name

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }
    }

    private var onItemClickListener :((Hotel)->Unit)?=null

    fun setOnItemClickListener(listener : (Hotel)->Unit){
        onItemClickListener = listener
    }
}
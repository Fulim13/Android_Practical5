package com.example.demo.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.data.Friend
import com.example.demo.databinding.ItemFriendBinding

class FriendAdapter (
    val fn: (ViewHolder, Friend) -> Unit = { _, _ -> }
) : ListAdapter<Friend, FriendAdapter.ViewHolder>(Diff) {

    companion object Diff : DiffUtil.ItemCallback<Friend>() {
        override fun areItemsTheSame(a: Friend, b: Friend) = a.id == b.id
        override fun areContentsTheSame(a: Friend, b: Friend) = a == b
    }

    class ViewHolder(val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val f = getItem(position)
        // TODO: Load image from blob
        // holder.binding.imgPhoto
        holder.binding.txtId.text = f.id
        holder.binding.txtName.text = f.name
        holder.binding.txtAge.text = f.age.toString()
        fn(holder, f)
    }

}

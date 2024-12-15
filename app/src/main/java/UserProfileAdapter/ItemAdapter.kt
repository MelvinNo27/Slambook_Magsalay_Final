package com.example.my_slambook1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val items: MutableList<Item>,
    private val onItemClick: (Item) -> Unit // Add a click listener function
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // ViewHolder for the RecyclerView
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarDisplay)
    }

    fun updateItem(updatedItem: Item) {
        val position = items.indexOfFirst { it.id == updatedItem.id }
        if (position != -1) {
            items[position] = updatedItem
            notifyItemChanged(position) // Notify that the item has been updated
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_profile, parent, false)
        return ItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.fullName

        item.avatar?.let {
            holder.avatarImageView.setImageDrawable(it)
        }

        // Set the item click listener
        holder.itemView.setOnClickListener {
            onItemClick(item)  // Trigger the item click handler in the activity
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Method to add a new item to the list
    fun addItem(item: Item) {
        items.add(item)
        notifyItemInserted(items.size - 1)  // Notify the adapter that an item has been added
    }
}

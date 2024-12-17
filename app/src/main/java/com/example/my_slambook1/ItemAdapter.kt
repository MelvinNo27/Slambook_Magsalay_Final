package com.example.my_slambook1

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val context: Context,
    private val items: MutableList<Item>,
    private val onItemClick: (Item) -> Unit,
    private val onDeleteClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarDisplay)
        val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)
    }

    fun updateItem(updatedItem: Item) {
        val index = items.indexOfFirst { it.id == updatedItem.id }
        if (index != -1) {
            items[index] = updatedItem
            notifyItemChanged(index)
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

        // Set the delete click listener with confirmation dialog
        holder.deleteIcon.setOnClickListener {
            showDeleteConfirmationDialog(item)  // Show delete confirmation dialog
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

    // Method to remove an item from the list
    fun removeItem(item: Item) {
        val position = items.indexOf(item)
        if (position != -1) {
            items.removeAt(position)
            notifyItemRemoved(position)  // Notify the adapter that an item has been removed
        }
    }

    // Show a confirmation dialog before deleting the item
    private fun showDeleteConfirmationDialog(item: Item) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Yes") { dialog, _ ->
                // If "Yes" is clicked, delete the item
                onDeleteClick(item)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                // If "No" is clicked, dismiss the dialog
                dialog.dismiss()
            }
        builder.create().show()
    }
}

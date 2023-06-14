package com.example.sort1111.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.BakeMate.R



import com.example.sort1111.Domain.CategoryDomain


class CategoryAdapter(private val categoryDomains: ArrayList<CategoryDomain>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, category: String, content: String)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        val categoryPic: ImageView = itemView.findViewById(R.id.categoryPic)
        val mainLayout: ConstraintLayout = itemView.findViewById(R.id.mainLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cat, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = categoryDomains[position].title
        var picUrl = ""
        when (position) {
            0 -> {
                picUrl = "cat_01"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.category_background11)
            }
            1 -> {
                picUrl = "cat_02"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.category_background22)
            }
            2 -> {
                picUrl = "cat_03"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.category_background33)
            }
            3 -> {
                picUrl = "cat_5"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.category_background55)
            }
            4 -> {
                picUrl = "cat_04"
                holder.mainLayout.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.category_background44)
            }

        }

        val drawableResourceId =
            holder.itemView.context.resources.getIdentifier(picUrl, "drawable", holder.itemView.context.packageName)

        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.categoryPic)

        holder.itemView.setOnClickListener {
            listener?.onItemClick(position, categoryDomains[position].title, "This is ${categoryDomains[position].title} category.")
        }

    }

    override fun getItemCount(): Int {
        return categoryDomains.size
    }
}




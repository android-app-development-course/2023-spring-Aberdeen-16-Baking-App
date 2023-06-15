package com.example.sort1111.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.BakeMate.R
import com.example.sort1111.Domain.FastDeliveryDomain

class FastDeliveryAdapter(internal val fastDeliveryDomains: ArrayList<FastDeliveryDomain>) :
    RecyclerView.Adapter<FastDeliveryAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null
    private var context: Context? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(context)
            .inflate(R.layout.viewholder_fast_delivery, parent, false)

        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.time.text = "${fastDeliveryDomains[position].time} min"
        holder.title.text = fastDeliveryDomains[position].title
        holder.star.text = fastDeliveryDomains[position].star.toString()

        val drawableResourceId =
            context?.resources?.getIdentifier(
                fastDeliveryDomains[position].pic,
                "drawable",
                context?.packageName
            )
        context?.let {
            Glide.with(it)
                .load(drawableResourceId)
                .into(holder.pic)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount() = fastDeliveryDomains.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val pic: ImageView = itemView.findViewById(R.id.pic)
        val star: TextView = itemView.findViewById(R.id.star)
        val time: TextView = itemView.findViewById(R.id.time)
    }
}

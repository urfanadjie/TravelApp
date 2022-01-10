package com.mws.travelapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*

class CommentAdapter (private val listComment: ArrayList<CommentResponse>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
    inner class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(commentResponse: CommentResponse){
            with(itemView){
                val text = "\bPost ID : \t ${commentResponse.postId}\n" +
                        "Name : \t ${commentResponse.name}\n" +
                        "Email : \t ${commentResponse.email}\n" +
                        "Comment : \t ${commentResponse.body}"
                tvText.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

    override fun getItemCount(): Int = listComment.size

}


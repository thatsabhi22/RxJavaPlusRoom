package com.theleafapps.pro.rxjavaplusroom.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.theleafapps.pro.rxjavaplusroom.data.local.entity.StudentEntity

class StudentRecyclerAdapter : RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<StudentEntity>(){
        override fun areItemsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
            TODO("Not yet implemented")
        }
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(data: StudentEntity){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
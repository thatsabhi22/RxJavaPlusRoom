package com.theleafapps.pro.rxjavaplusroom.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.theleafapps.pro.rxjavaplusroom.R
import com.theleafapps.pro.rxjavaplusroom.data.local.entity.StudentEntity

class StudentRecyclerAdapter(
    private val editClickListener: (data: StudentEntity) -> Unit,
    private val deleteClickListener: (data: StudentEntity) -> Unit
) :
    RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<StudentEntity>() {
        override fun areItemsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_view, parent, false)
        return StudentViewHolder(view, editClickListener,deleteClickListener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    fun setList(newItem: List<StudentEntity>) {
        asyncListDiffer.submitList(newItem)
    }

    class StudentViewHolder(
        itemView: View,
        private val editClickListener: (data: StudentEntity) -> Unit,
        private val deleteClickListener: (data: StudentEntity) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val id = itemView.findViewById<TextView>(R.id.std_id)
        private val name = itemView.findViewById<TextView>(R.id.std_name)
        private val age = itemView.findViewById<TextView>(R.id.std_age)
        private val subject = itemView.findViewById<TextView>(R.id.std_subject)

        private val editBtn = itemView.findViewById<TextView>(R.id.btn_edit)
        private val deleteBtn = itemView.findViewById<TextView>(R.id.btn_delete)

        fun bind(data: StudentEntity) {
            id.text = data.id.toString()
            name.text = data.studentName.toString()
            age.text = data.age.toString()
            subject.text = data.subject.toString()

            editBtn.setOnClickListener {
                editClickListener(data)
            }
            deleteBtn.setOnClickListener {
                deleteClickListener(data)
            }
        }
    }
}
package com.example.studentmanager

import Student
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val students: List<Student>,
    private val onEditClick: (Student) -> Unit,
    private val onDeleteClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvStudentName)
        val idTextView: TextView = itemView.findViewById(R.id.tvStudentID)
        val editImageView: ImageView = itemView.findViewById(R.id.ivEditStudent)
        val deleteImageView: ImageView = itemView.findViewById(R.id.ivDeleteStudent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameTextView.text = student.name
        holder.idTextView.text = student.id

        holder.editImageView.setOnClickListener { onEditClick(student) }
        holder.deleteImageView.setOnClickListener { onDeleteClick(student) }
    }

    override fun getItemCount(): Int = students.size
}

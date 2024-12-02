package com.example.studentmanager

import Student
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class EditStudentFragment : Fragment() {

    private lateinit var studentNameEditText: EditText
    private lateinit var studentIdEditText: EditText
    private lateinit var editStudentButton: Button
    private lateinit var student: Student

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_edit_student, container, false)

        studentNameEditText = rootView.findViewById(R.id.editTextStudentName)
        studentIdEditText = rootView.findViewById(R.id.editTextStudentId)
        editStudentButton = rootView.findViewById(R.id.buttonEditStudent)

        student = arguments?.getSerializable("student") as Student
        studentNameEditText.setText(student.name)
        studentIdEditText.setText(student.id)

        editStudentButton.setOnClickListener {
            val updatedName = studentNameEditText.text.toString()
            val updatedId = studentIdEditText.text.toString()

            if (updatedName.isNotEmpty() && updatedId.isNotEmpty()) {
                student.name = updatedName
                student.id = updatedId
                (activity as? MainActivity)?.updateStudent(student) // Cập nhật sinh viên trong danh sách
                requireActivity().onBackPressed() // Quay lại StudentListFragment
            }
        }

        return rootView
    }
}


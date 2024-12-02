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

class AddStudentFragment : Fragment() {

    private lateinit var studentNameEditText: EditText
    private lateinit var studentIdEditText: EditText
    private lateinit var addStudentButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_student, container, false)

        studentNameEditText = rootView.findViewById(R.id.editTextStudentName)
        studentIdEditText = rootView.findViewById(R.id.editTextStudentId)
        addStudentButton = rootView.findViewById(R.id.buttonAddStudent)

        addStudentButton.setOnClickListener {
            val studentName = studentNameEditText.text.toString()
            val studentId = studentIdEditText.text.toString()

            if (studentName.isNotEmpty() && studentId.isNotEmpty()) {
                val student = Student(studentName, studentId)
                (activity as? MainActivity)?.addStudent(student) // Gọi phương thức addStudent từ MainActivity
                requireActivity().onBackPressed() // Quay lại StudentListFragment
            }
        }

        return rootView
    }
}


package com.example.tim_kiem_trong_danh_sach

import Student
import StudentAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var editTextSearch: EditText
    private lateinit var recyclerViewStudents: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = listOf(
        Student("Nguyen Van A", "20212021"),
        Student("Le Thi B", "20222022"),
        Student("Tran Van C", "20232023"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextSearch = findViewById(R.id.editTextSearch)
        recyclerViewStudents = findViewById(R.id.recyclerViewStudents)

        studentAdapter = StudentAdapter(studentList)
        recyclerViewStudents.layoutManager = LinearLayoutManager(this)
        recyclerViewStudents.adapter = studentAdapter

        // Thêm sự kiện tìm kiếm
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString()
                if (searchText.length > 2) {
                    val filteredList = studentList.filter {
                        it.name.contains(searchText, ignoreCase = true) ||
                                it.studentId.contains(searchText)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(studentList) // Hiển thị toàn bộ danh sách
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}

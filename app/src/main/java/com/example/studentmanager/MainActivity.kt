package com.example.studentmanager

import Student
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, StudentListFragment())
            .commit()
    }

    fun addStudent(student: Student) {
        // Thêm sinh viên vào danh sách trong StudentListFragment
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? StudentListFragment
        fragment?.addStudent(student)
    }

    fun updateStudent(student: Student) {
        // Cập nhật sinh viên trong danh sách trong StudentListFragment
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? StudentListFragment
        fragment?.updateStudent(student)
    }
}


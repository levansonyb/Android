package com.example.studentmanager

import Student
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = arrayListOf(
        Student("Nguyễn Văn An", "SV001"),
        Student("Trần Thị Bảo", "SV002"),
        Student("Lê Hoàng Cường", "SV003"),
        Student("Phạm Thị Dung", "SV004"),
        Student("Đỗ Minh Đức", "SV005"),
        Student("Vũ Thị Hoa", "SV006"),
        Student("Hoàng Văn Hải", "SV007"),
        Student("Bùi Thị Hạnh", "SV008"),
        Student("Đinh Văn Hùng", "SV009"),
        Student("Nguyễn Thị Linh", "SV010"),
        Student("Phạm Văn Long", "SV011")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_student_list, container, false)

        // Thiết lập RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Thiết lập Adapter
        studentAdapter = StudentAdapter(studentList,
            onEditClick = { student -> editStudent(student) },
            onDeleteClick = { student -> deleteStudent(student) }
        )
        recyclerView.adapter = studentAdapter

        // Xử lý sự kiện nút thêm sinh viên
        rootView.findViewById<FloatingActionButton>(R.id.fabAddStudent).setOnClickListener {
            // Điều hướng đến fragment thêm sinh viên
            // Ví dụ: Navigation.findNavController(it).navigate(R.id.action_to_addStudentFragment)
        }

        return rootView
    }

    // Hàm xử lý chỉnh sửa sinh viên
    private fun editStudent(student: Student) {
        // Điều hướng đến fragment chỉnh sửa sinh viên
        // Có thể truyền `student` qua bundle để chỉnh sửa
    }

    // Hàm xử lý xóa sinh viên
    private fun deleteStudent(student: Student) {
        studentList.remove(student)
        studentAdapter.notifyDataSetChanged()
    }

    // Hàm thêm sinh viên
    fun addStudent(student: Student) {
        studentList.add(0, student) // Thêm vào đầu danh sách
        studentAdapter.notifyItemInserted(0) // Cập nhật RecyclerView
        recyclerView.scrollToPosition(0) // Cuộn lên đầu danh sách
    }
    // Hàm cập nhật sinh viên
    fun updateStudent(student: Student) {
        val index = studentList.indexOfFirst { it.id == student.id }
        if (index != -1) {
            studentList[index] = student // Cập nhật thông tin
            studentAdapter.notifyItemChanged(index) // Cập nhật RecyclerView
        } else {
            // Nếu sinh viên không tồn tại, có thể xử lý lỗi tại đây (nếu cần)
            println("Student with ID ${student.id} not found")
        }
    }
}

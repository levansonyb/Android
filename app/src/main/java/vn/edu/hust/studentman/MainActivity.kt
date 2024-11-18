package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val students = mutableListOf(
      StudentModel("Nguyễn Văn An", "SV001"),
      StudentModel("Trần Thị Bảo", "SV002"),
      StudentModel("Lê Hoàng Cường", "SV003"),
      StudentModel("Phạm Thị Dung", "SV004"),
      StudentModel("Đỗ Minh Đức", "SV005"),
      StudentModel("Vũ Thị Hoa", "SV006"),
      StudentModel("Hoàng Văn Hải", "SV007"),
      StudentModel("Bùi Thị Hạnh", "SV008"),
      StudentModel("Đinh Văn Hùng", "SV009"),
      StudentModel("Nguyễn Thị Linh", "SV010"),
      StudentModel("Phạm Văn Long", "SV011"),
      StudentModel("Trần Thị Mai", "SV012"),
      StudentModel("Lê Thị Ngọc", "SV013"),
      StudentModel("Vũ Văn Nam", "SV014"),
      StudentModel("Hoàng Thị Phương", "SV015"),
      StudentModel("Đỗ Văn Quân", "SV016"),
      StudentModel("Nguyễn Thị Thu", "SV017"),
      StudentModel("Trần Văn Tài", "SV018"),
      StudentModel("Phạm Thị Tuyết", "SV019"),
      StudentModel("Lê Văn Vũ", "SV020")
    )

  private lateinit var studentAdapter: StudentAdapter
  private var recentlyDeletedStudent: StudentModel? = null
  private var recentlyDeletedPosition: Int = -1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Setup RecyclerView
    studentAdapter = StudentAdapter(students,
      onEdit = { student, position -> showAddEditDialog(student, position) },
      onDelete = { student, position -> showDeleteDialog(student, position) }
    )
    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

    // Add new student button
    findViewById<Button>(R.id.btn_add_new).setOnClickListener {
      showAddEditDialog()
    }
  }

  private fun showAddEditDialog(student: StudentModel? = null, position: Int = -1) {
    val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_student, null)
    val edtName = dialogView.findViewById<EditText>(R.id.edt_name)
    val edtId = dialogView.findViewById<EditText>(R.id.edt_id)

    student?.let {
      edtName.setText(it.studentName)
      edtId.setText(it.studentId)
    }

    AlertDialog.Builder(this)
      .setTitle(if (student == null) "Add Student" else "Edit Student")
      .setView(dialogView)
      .setPositiveButton("Save") { dialog, _ ->
        val name = edtName.text.toString()
        val id = edtId.text.toString()

        if (student == null) {
          students.add(StudentModel(name, id))
        } else {
          students[position] = StudentModel(name, id)
        }
        studentAdapter.notifyDataSetChanged()
        dialog.dismiss()
      }
      .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
      .show()
  }

  private fun showDeleteDialog(student: StudentModel, position: Int) {
    AlertDialog.Builder(this)
      .setTitle("Delete Student")
      .setMessage("Are you sure you want to delete ${student.studentName}?")
      .setPositiveButton("Delete") { dialog, _ ->
        recentlyDeletedStudent = student
        recentlyDeletedPosition = position
        students.removeAt(position)
        studentAdapter.notifyItemRemoved(position)
        showUndoSnackbar()
        dialog.dismiss()
      }
      .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
      .show()
  }

  private fun showUndoSnackbar() {
    Snackbar.make(findViewById(R.id.main), "Student deleted", Snackbar.LENGTH_LONG)
      .setAction("Undo") {
        recentlyDeletedStudent?.let {
          students.add(recentlyDeletedPosition, it)
          studentAdapter.notifyItemInserted(recentlyDeletedPosition)
        }
      }.show()
  }
}
package com.example.danh_sach_don_gian

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var textViewError: TextView
    private lateinit var listViewNumbers: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        textViewError = findViewById(R.id.textViewError)
        listViewNumbers = findViewById(R.id.listViewNumbers)

        buttonShow.setOnClickListener { showNumbers() }
    }

    private fun showNumbers() {
        // Ẩn thông báo lỗi mỗi khi nhấn nút
        textViewError.visibility = View.GONE
        val input = editTextNumber.text.toString()

        // Kiểm tra nếu ô nhập trống
        if (input.isEmpty()) {
            textViewError.text = "Vui lòng nhập số nguyên dương."
            textViewError.visibility = View.VISIBLE
            return
        }

        // Chuyển đổi input thành số nguyên và kiểm tra tính hợp lệ
        val n = input.toIntOrNull()
        if (n == null || n <= 0) {
            textViewError.text = "Dữ liệu không hợp lệ."
            textViewError.visibility = View.VISIBLE
            listViewNumbers.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, emptyList<Int>())
            return
        }

        // Kiểm tra loại RadioButton đã chọn và thực hiện xử lý tương ứng
        val numbers = when (radioGroup.checkedRadioButtonId) {
            R.id.radioEven -> (0..n).filter { it % 2 == 0 } // Số chẵn
            R.id.radioOdd -> (1..n).filter { it % 2 != 0 }  // Số lẻ
            R.id.radioSquare -> (0..n).map { it * it }.takeWhile { it <= n } // Số chính phương
            else -> {
                textViewError.text = "Vui lòng chọn loại số."
                textViewError.visibility = View.VISIBLE
                return
            }
        }

        // Cập nhật ListView với danh sách các số thỏa mãn điều kiện
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        listViewNumbers.adapter = adapter
    }
}

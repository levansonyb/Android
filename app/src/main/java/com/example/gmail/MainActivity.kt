// MainActivity.kt
package com.example.gmail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gmail.adapter.EmailAdapter
import com.example.gmail.model.Email
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cài đặt toolbar
//        setSupportActionBar(toolbar)

        // Dữ liệu giả lập cho email
        val emails = listOf(
            Email("Edurila.com", "Are you looking to Learn Web Designing", "12:34 PM", "E"),
            Email("Chris Abad", "Help make Campaign Monitor better", "11:22 AM", "C"),
            Email("Tuto.com", "8h de formation gratuite", "11:04 AM", "T"),
            Email("Support", "Société Ovh: suivi de vos services", "10:26 AM", "S"),
            Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM", "M")
        )

//        // Thiết lập RecyclerView
//        recycler_view.layoutManager = LinearLayoutManager(this)
//        recycler_view.adapter = EmailAdapter(emails)
     }
}

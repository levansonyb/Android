// adapter/EmailAdapter.kt
package com.example.gmail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.example.gmail.ItemEmailBinding
import com.example.gmail.model.Email

class EmailAdapter(private val emails: List<Email>) :
    RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val binding = ItemEmailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(emails[position])
    }

    override fun getItemCount(): Int = emails.size

    class EmailViewHolder(private val binding: ItemEmailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(email: Email) {
//            binding.senderIcon.text = email.icon
//            binding.senderName.text = email.sender
//            binding.emailContent.text = email.content
//            binding.emailTime.text = email.time
        }
    }
}

class ItemEmailBinding {
    val root: View
        get() {
            TODO()
        }

    companion object {
        fun inflate(from: LayoutInflater?, parent: ViewGroup, b: Boolean): ItemEmailBinding {
            TODO("Not yet implemented")
        }
    }

}

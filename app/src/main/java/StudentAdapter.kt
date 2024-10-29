import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    fun updateList(newList: List<Student>) {
        studentList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.nameTextView.text = student.name
        holder.idTextView.text = student.studentId
    }

    override fun getItemCount(): Int = studentList.size

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(android.R.id.text1)
        val idTextView: TextView = itemView.findViewById(android.R.id.text2)
    }
}

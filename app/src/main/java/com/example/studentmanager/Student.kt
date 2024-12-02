import java.io.Serializable

data class Student(
    var name: String,
    var id: String
) : Serializable {

    override fun toString(): String {
        return "$name - $id"
    }
}


data class Question(
    val question: String,
    val istrue: Boolean,
    var answered: Boolean = false,
    var correct: Boolean = false,
)

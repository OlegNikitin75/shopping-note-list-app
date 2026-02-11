package alex.dev.shoppingnotelistapp.errors

sealed interface AppError {
    val userMessage: String

    data class ConstraintViolation(override val userMessage: String) : AppError
    data class DatabaseError(override val userMessage: String) : AppError
    data class Unknown(override val userMessage: String) : AppError
}
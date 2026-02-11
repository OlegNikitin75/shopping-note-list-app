package alex.dev.shoppingnotelistapp.utils

import alex.dev.shoppingnotelistapp.errors.AppError

sealed class AppResult<out T> {
    object Loading : AppResult<Nothing>()
    data class Success<T>(val data: T) : AppResult<T>()
    data class Failure<T>(val error: AppError) : AppResult<Nothing>()
}
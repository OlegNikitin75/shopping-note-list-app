package alex.dev.shoppingnotelistapp.data.repository.impl

import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.ShoppingListDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.data.repository.ShoppingListRepository
import alex.dev.shoppingnotelistapp.errors.AppError
import alex.dev.shoppingnotelistapp.utils.AppResult
import android.database.sqlite.SQLiteConstraintException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListRepoImpl @Inject constructor(
    private val dao: ShoppingListDao
) : ShoppingListRepository {
    override fun getAllShoppingLists(): Flow<List<ShoppingList>> {
        return dao.getAllShoppingLists()
    }

    override fun getShoppingListById(listId: Long): Flow<ShoppingList?> {
        return dao.getShoppingListById(listId)
    }

    override suspend fun insertShoppingList(shoppingList: ShoppingList): AppResult<Long> {
        return try {
            val rowId = dao.insertShoppingList(shoppingList)
            AppResult.Success(rowId)
        } catch (e: SQLiteConstraintException) {
            AppResult.Failure<Long>(AppError.ConstraintViolation("Название уже используется другим списком"))
        } catch (e: Exception) {
            AppResult.Failure<Long>(
                AppError.DatabaseError(
                    e.localizedMessage ?: "Ошибка создания нового списка"
                )
            )
        }
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingList): AppResult<Unit> {
        return try {
            dao.updateShoppingList(shoppingList)
            AppResult.Success(Unit)
        } catch (e: SQLiteConstraintException) {
            AppResult.Failure<Unit>(AppError.ConstraintViolation("Название уже используется другим списком"))
        } catch (e: Exception) {
            AppResult.Failure<Unit>(
                AppError.DatabaseError(
                    e.localizedMessage ?: "Ошибка обновления"
                )
            )
        }
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingList): AppResult<Unit> {
        return try {
            dao.deleteShoppingList(shoppingList)
            AppResult.Success(Unit)
        } catch (e: Exception) {
            AppResult.Failure<Unit>(AppError.DatabaseError(e.localizedMessage ?: "Ошибка удаления"))
        }
    }
}
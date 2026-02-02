package alex.dev.shoppingnotelistapp.data.data_source.local.room.dao

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.NoteItem
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteItemDao {
    //Запись заметки
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteItem(item: NoteItem)

    //Обновление заметки
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNoteItem(item: NoteItem)

    //Удаление заметки
    @Delete
    suspend fun deleteNoteItem(item: NoteItem)

    //Получение всех заметок
    @Query("SELECT * FROM note_items")
    fun getAllNoteItems(): Flow<List<NoteItem>>

    //Получение заметки по Id
    @Query("SELECT * FROM note_items WHERE note_id = :noteId")
    suspend fun getNoteItemById(noteId: Int): NoteItem

}
package alex.dev.shoppingnotelistapp.data.repository

import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.NoteItem
import kotlinx.coroutines.flow.Flow

interface NoteItemRepository {
    suspend fun insertNoteItem(item: NoteItem)
    suspend fun deleteNoteItem(item: NoteItem)
    fun getAllNoteItems(): Flow<List<NoteItem>>
    suspend fun getNoteItemById(id: Long): NoteItem
}
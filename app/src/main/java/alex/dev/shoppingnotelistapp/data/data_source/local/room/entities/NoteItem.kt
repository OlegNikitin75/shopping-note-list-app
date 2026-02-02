package alex.dev.shoppingnotelistapp.data.data_source.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_items")
data class NoteItem(
    @ColumnInfo("note_id")
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val description: String,
    val time: String,
)

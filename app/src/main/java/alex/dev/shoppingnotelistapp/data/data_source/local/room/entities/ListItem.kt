package alex.dev.shoppingnotelistapp.data.data_source.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_items")
data class ListItem(
    @ColumnInfo("list_item_id")
    @PrimaryKey
    val id: Int,
    val name: String,
    val isChecked: Boolean,
    val listId: Int,
)
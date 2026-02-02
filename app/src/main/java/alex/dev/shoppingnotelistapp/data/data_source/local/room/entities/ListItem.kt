package alex.dev.shoppingnotelistapp.data.data_source.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ListItem(
    @ColumnInfo("item_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val measurement: String,
    val isChecked: Boolean,
    val listId: Int,
    val storeId: Int
)
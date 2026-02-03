package alex.dev.shoppingnotelistapp.data.data_source.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ShoppingListItem(
    @ColumnInfo("item_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("item_name")
    val name: String,
    val quantity: Int,
    val measurement: String,
    @ColumnInfo(name = "is_checked")
    val isChecked: Boolean,
    @ColumnInfo(name = "list_id")
    val listId: Int,
    @ColumnInfo(name = "fk_store_id")
    val storeId: Int
)
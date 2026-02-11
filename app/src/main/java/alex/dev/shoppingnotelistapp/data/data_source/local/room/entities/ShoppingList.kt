package alex.dev.shoppingnotelistapp.data.data_source.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_lists",
    indices = [
        Index(value = ["shopping_list_name"], unique = true)
    ]
)
data class ShoppingList(
    @ColumnInfo("shopping_list_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "shopping_list_name")
    val name: String,
    val createdAt: Long = System.currentTimeMillis(),
    val allItemsCount: Int = 0,
    val allSelectedItemsCount: Int = 0
)

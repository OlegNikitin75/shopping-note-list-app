package alex.dev.shoppingnotelistapp.data.data_source.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_lists")
data class ShoppingList(
    @ColumnInfo("shopping_list_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "shopping_list_name")
    val name: String,
    val createdAt: Long = 0L,
    val allItemsCount: Int,
    val allSelectedItemsCount: Int
)

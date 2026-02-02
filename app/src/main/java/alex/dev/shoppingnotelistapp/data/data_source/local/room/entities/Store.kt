package alex.dev.shoppingnotelistapp.data.data_source.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stores")
data class Store(
    @ColumnInfo("store_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val listId: Int,
)
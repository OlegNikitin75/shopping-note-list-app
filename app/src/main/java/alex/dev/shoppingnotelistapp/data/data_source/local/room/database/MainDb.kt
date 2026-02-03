package alex.dev.shoppingnotelistapp.data.data_source.local.room.database

import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.NoteItemDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.ShoppingListDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.ShoppingListItemDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.StoreDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.NoteItem
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingListItem
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.Store
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingList::class, ShoppingListItem::class, NoteItem::class, Store::class],
    version = 1,
    exportSchema = false
)
abstract class MainDb : RoomDatabase() {
    abstract val shoppingListDao: ShoppingListDao
    abstract val shoppingListItemDao: ShoppingListItemDao
    abstract val noteItemDao: NoteItemDao
    abstract val storeDao: StoreDao
}
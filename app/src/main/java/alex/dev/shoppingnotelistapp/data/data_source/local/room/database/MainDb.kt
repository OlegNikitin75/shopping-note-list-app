package alex.dev.shoppinglistapp.data.local.database

import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.ListItemDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.NoteItemDao
import alex.dev.shoppingnotelistapp.data.data_source.local.room.dao.ShoppingListDao
import alex.dev.shoppinglistapp.data.local.entities.ListItem
import alex.dev.shoppinglistapp.data.local.entities.NoteItem
import alex.dev.shoppinglistapp.data.local.entities.ShoppingList
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingList::class, ListItem::class, NoteItem::class],
    version = 1
)
abstract class MainDb : RoomDatabase() {
    abstract val shoppingListDao: ShoppingListDao
    abstract val listItemDao: ListItemDao
    abstract val noteItemDao: NoteItemDao
}
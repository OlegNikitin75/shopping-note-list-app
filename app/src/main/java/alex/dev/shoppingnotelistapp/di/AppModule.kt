package alex.dev.shoppingnotelistapp.di

import alex.dev.shoppingnotelistapp.data.data_source.local.room.database.MainDb
import alex.dev.shoppingnotelistapp.data.repository.NoteItemRepository
import alex.dev.shoppingnotelistapp.data.repository.ShoppingListItemRepository
import alex.dev.shoppingnotelistapp.data.repository.ShoppingListRepository
import alex.dev.shoppingnotelistapp.data.repository.impl.NoteItemRepoImpl
import alex.dev.shoppingnotelistapp.data.repository.impl.ShoppingListItemRepoImpl
import alex.dev.shoppingnotelistapp.data.repository.impl.ShoppingListRepoImpl
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainDb(@ApplicationContext context: Context): MainDb {
        return Room.databaseBuilder(
            context,
            MainDb::class.java,
            "shopping_list_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideShoppingListRepo(db: MainDb): ShoppingListRepository {
        return ShoppingListRepoImpl(db.shoppingListDao)
    }

    @Provides
    @Singleton
    fun provideShoppingListItemRepo(db: MainDb): ShoppingListItemRepository {
        return ShoppingListItemRepoImpl(db.shoppingListItemDao)
    }

    @Provides
    @Singleton
    fun provideNoteItemRepo(db: MainDb): NoteItemRepository {
        return NoteItemRepoImpl(db.noteItemDao)
    }
}
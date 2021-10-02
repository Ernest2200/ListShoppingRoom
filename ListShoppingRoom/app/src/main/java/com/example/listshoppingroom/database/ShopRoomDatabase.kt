package com.example.listshoppingroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.listshoppingroom.dao.ListDao
import com.example.listshoppingroom.entities.ListShop
// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [ListShop::class], version = 1, exportSchema = false)
abstract class ShopRoomDatabase :RoomDatabase() {
    abstract fun ListDao(): ListDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: ShopRoomDatabase? = null
        fun getDatabase(context: Context): ShopRoomDatabase{
// if the INSTANCE is not null, then return it,
// if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShopRoomDatabase::class.java,
                    "list_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
// return instance
                instance
            }
        }
    }
}
package com.example.listshoppingroom.repository
import android.content.Context
import com.example.listshoppingroom.dao.ListDao
import com.example.listshoppingroom.database.ShopRoomDatabase
import com.example.listshoppingroom.entities.ListShop
import kotlinx.coroutines.flow.Flow


// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ListShopRepository(private val listDao: ListDao) {
    companion object {
        private var INSTANCE : ListShopRepository? = null
        fun getRepository(context: Context) : ListShopRepository {
            return INSTANCE ?: synchronized(this) {
                val database = ShopRoomDatabase.getDatabase(context)
                val instance = ListShopRepository(database.ListDao())
                INSTANCE = instance
                instance
            }
        }
    }
    // Room executes all queries on a separate thread.
// Observed Flow will notify the observer when the data has changed.
    val allListShop: Flow<List<ListShop>> = listDao.getAlphabetizedVehicles()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
// implement anything else to ensure we're not doing long running database work
// off the main thread.
    suspend fun insert(product: ListShop) {
        listDao.insert(product)
    }
    suspend fun deleteAll() {
        listDao.deleteAll()
    }
    fun getTotal(): Double {
        return listDao.getTotalExpense()
    }
    suspend fun deleteOne(id:Int) {
        listDao.deleteOne(id)
    }
}
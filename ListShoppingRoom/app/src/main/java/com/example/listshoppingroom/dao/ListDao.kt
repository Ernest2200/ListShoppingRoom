package com.example.listshoppingroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.listshoppingroom.entities.ListShop
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {
    @Query("SELECT * FROM product_table ORDER BY name ASC")
    fun getAlphabetizedVehicles(): Flow<List<ListShop>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: ListShop)
    @Query("DELETE FROM product_table")
    suspend fun deleteAll()
    @Query("SELECT SUM(price) FROM product_table")
    fun getTotalExpense():Double
    @Query("DELETE FROM product_table WHERE id=:id")
    suspend fun deleteOne(id:Int)

}
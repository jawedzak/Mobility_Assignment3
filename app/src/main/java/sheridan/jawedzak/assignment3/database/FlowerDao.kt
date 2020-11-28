package sheridan.jawedzak.assignment3.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FlowerDao {
    @Query("SELECT * FROM flowersList")
    fun getAll(): LiveData<List<DatabaseEntities>>

    @Query("SELECT * FROM flowersList WHERE name=:name")
    suspend fun get(name: String): DatabaseEntities

    @Delete
    suspend fun delete(flower: DatabaseEntities)

    @Query("DELETE FROM flowersList WHERE name=:name")
    suspend fun delete(name: String)

    @Update
    suspend fun update(flower: DatabaseEntities)

    @Insert
    suspend fun insert(flower: DatabaseEntities): Long }
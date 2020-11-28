package sheridan.jawedzak.assignment3.database

import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database

@Database(entities = [DatabaseEntities::class], version = 1)
abstract class FlowerDatabase : RoomDatabase(){
    abstract fun flowerDao(): FlowerDao

    companion object {
        @Volatile private var INSTANCE: FlowerDatabase? = null
        fun getInstance(context: Context): FlowerDatabase {
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val instances = Room.databaseBuilder(
                    context,
                    FlowerDatabase::class.java,
                    "donut_database"
                ).build()
                INSTANCE = instances
                return instances } }
    }
}
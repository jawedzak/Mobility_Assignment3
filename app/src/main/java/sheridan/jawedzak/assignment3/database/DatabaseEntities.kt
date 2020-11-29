package sheridan.jawedzak.assignment3.database

import androidx.room.*

// tablename flowerList

@Entity(tableName = "flowersList")
data class DatabaseEntities (
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "label")
    val label: String,

    @ColumnInfo(name = "price")
    val price: String,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "img")
    val img: String)
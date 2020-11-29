package sheridan.jawedzak.assignment3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import sheridan.jawedzak.assignment3.database.DatabaseEntities
import sheridan.jawedzak.assignment3.database.FlowerDatabase
import sheridan.jawedzak.assignment3.domain.Flower

class FlowerRepository (private val db: FlowerDatabase){

    private val flowerDao = db.flowerDao()

    suspend fun get(name:String):Flower{
        return flowerDao.get(name).toFlower()
    }

    suspend fun update(flower: Flower){
        flowerDao.update(flower.toFlowerEntity())
    }

    suspend fun getAll(): LiveData<List<Flower>> {

        return Transformations.map(flowerDao.getAll()) { list ->
            list.map { it.toFlower() }
        } }

    suspend fun insert(flower: Flower):Long{
        return flowerDao.insert(flower.toFlowerEntity())
    }

    fun DatabaseEntities.toFlower(): Flower{
        return Flower(name, label, price, text, img)
    }

    fun Flower.toFlowerEntity(): DatabaseEntities{
        return DatabaseEntities(name, label, price, text, img)
    }
}
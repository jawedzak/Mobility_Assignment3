package sheridan.jawedzak.assignment3.detail

import android.app.Application
import androidx.lifecycle.*
import sheridan.jawedzak.assignment3.network.FlowerJson

class DetailViewModel(flowersProperty: FlowerJson,
                      app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<FlowerJson>()
    val selectedProperty: LiveData<FlowerJson>
        get() = _selectedProperty

    init {
        _selectedProperty.value = flowersProperty
    }

    val displayPropertyLabel = Transformations.map(selectedProperty) {
        it.label
    }

    val displayPropertyPrice = Transformations.map(selectedProperty) {
        it.price
    }

    val displayPropertyText = Transformations.map(selectedProperty) {
        it.text
    }
}
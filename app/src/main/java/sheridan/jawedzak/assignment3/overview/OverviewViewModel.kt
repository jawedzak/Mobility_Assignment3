package sheridan.jawedzak.assignment3.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sheridan.jawedzak.assignment3.network.FlowersApi
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sheridan.jawedzak.assignment3.network.FlowerJson

enum class FlowersApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<FlowersApiStatus>()

    val status: LiveData<FlowersApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<FlowerJson>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<FlowerJson>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<FlowerJson>()
    val navigateToSelectedProperty: LiveData<FlowerJson>
        get() = _navigateToSelectedProperty

    init {
        getFlowersRealEstateProperties()
    }

    private fun getFlowersRealEstateProperties() {

        viewModelScope.launch {
            _status.value = FlowersApiStatus.LOADING
            try {
                _properties.value = FlowersApi.retrofitService.getProperties().flowers
                _status.value = FlowersApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FlowersApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
    fun displayPropertyDetails(flowersProperty: FlowerJson) {
        _navigateToSelectedProperty.value = flowersProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}

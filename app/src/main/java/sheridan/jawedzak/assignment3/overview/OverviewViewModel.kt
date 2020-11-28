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

    private val _status = MutableLiveData<FlowersApiStatus>()

    val status: LiveData<FlowersApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<FlowerJson>>()

    val properties: LiveData<List<FlowerJson>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<FlowerJson>()
    val navigateToSelectedProperty: LiveData<FlowerJson>
        get() = _navigateToSelectedProperty

    init {
        getFlowersProperties()
    }

    private fun getFlowersProperties() {

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

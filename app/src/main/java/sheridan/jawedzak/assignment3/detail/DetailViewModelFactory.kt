package sheridan.jawedzak.assignment3.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sheridan.jawedzak.assignment3.network.FlowerJson

class DetailViewModelFactory(
        private val flowersProperty: FlowerJson,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(flowersProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

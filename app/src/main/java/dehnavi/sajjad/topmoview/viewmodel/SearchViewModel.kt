package dehnavi.sajjad.topmoview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.topmoview.model.home.ResponseMoviesList
import dehnavi.sajjad.topmoview.repository.SearchRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) :
    ViewModel() {

    val search = MutableLiveData<ResponseMoviesList>()
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()

    fun searchMovie(name: String) = viewModelScope.launch {
        val response = repository.searchMovie(name)
        loading.postValue(true)
        if (response.isSuccessful) {
            if (response.body()?.data!!.isNotEmpty()) {
                search.postValue(response.body())
                loading.postValue(false)
                empty.postValue(false)
            } else {
                empty.postValue(true)
            }
        }
    }
}
package dehnavi.sajjad.topmoview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.topmoview.model.home.ResponseGenreList
import dehnavi.sajjad.topmoview.model.home.ResponseMoviesList
import dehnavi.sajjad.topmoview.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val topMoviesList = MutableLiveData<ResponseMoviesList>()
    val listMovies = MutableLiveData<ResponseMoviesList>()
    val genreList = MutableLiveData<ResponseGenreList>()
    val loading = MutableLiveData<Boolean>()

    fun getTopMovieList(id: Int) = viewModelScope.launch {
        val responseMoviesList = repository.topMoviesList(id)
        if (responseMoviesList.isSuccessful) {
            topMoviesList.postValue(responseMoviesList.body())
        }
    }

    fun getListMovies() = viewModelScope.launch {
        loading.postValue(true)
        val responseListMovies = repository.listMovies()
        if (responseListMovies.isSuccessful) {
            loading.postValue(false)
            listMovies.postValue(responseListMovies.body())
        }
    }

    fun getGenreList() = viewModelScope.launch {
        val responseGenreList = repository.genreList()
        if (responseGenreList.isSuccessful) {
            genreList.postValue(responseGenreList.body())
        }
    }
}
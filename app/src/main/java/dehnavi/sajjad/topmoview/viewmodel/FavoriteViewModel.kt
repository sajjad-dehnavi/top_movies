package dehnavi.sajjad.topmoview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.topmoview.database.entity.MovieEntity
import dehnavi.sajjad.topmoview.model.home.ResponseMoviesList
import dehnavi.sajjad.topmoview.repository.FavoriteRepository
import dehnavi.sajjad.topmoview.repository.RegisterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FavoriteRepository) :
    ViewModel() {

    val empty = MutableLiveData<Boolean>()
    val favoriteMovies = MutableLiveData<List<MovieEntity>>()

    fun getAllMoise() = viewModelScope.launch {
        val list = repository.getAllFavorite()
        if (list.isNotEmpty()) {
            favoriteMovies.postValue(list)
            empty.postValue(false)
        } else
            empty.postValue(true)
    }
}
package dehnavi.sajjad.topmoview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.topmoview.database.entity.MovieEntity
import dehnavi.sajjad.topmoview.model.detail.ResponseDetailMovie
import dehnavi.sajjad.topmoview.model.home.ResponseMoviesList
import dehnavi.sajjad.topmoview.repository.DetailRepository
import dehnavi.sajjad.topmoview.repository.FavoriteRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.IDN
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    val movieDetail = MutableLiveData<ResponseDetailMovie>()
    val loading = MutableLiveData<Boolean>()
    val isFavorite = MutableLiveData<Boolean>()

    fun getMovieDetail(id: Int) = viewModelScope.launch {
        val response = repository.movieDetail(id)
        loading.postValue(true)
        if (response.isSuccessful) {
            movieDetail.postValue(response.body())
            loading.postValue(false)
        }
    }

   suspend fun existFavoriteMovie(id: Int) = withContext(viewModelScope.coroutineContext) {
       repository.existFavorite(id)
    }

    fun favoriteMovie(id: Int,entity: MovieEntity)=viewModelScope.launch {
        val exists =existFavoriteMovie(id)
        if (exists){
            isFavorite.postValue(false)
            repository.deleteFavorite(entity)
        }else{
            isFavorite.postValue(true)
            repository.insertFavorite(entity)
        }
    }
}
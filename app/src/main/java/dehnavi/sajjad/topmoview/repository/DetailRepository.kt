package dehnavi.sajjad.topmoview.repository

import dehnavi.sajjad.topmoview.api.ApiService
import dehnavi.sajjad.topmoview.database.dao.MoviesDao
import dehnavi.sajjad.topmoview.database.entity.MovieEntity
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: MoviesDao
) {

    suspend fun movieDetail(id: Int) =apiService.movieDetail(id)

    suspend fun insertFavorite(entity: MovieEntity) = dao.insertMovie(entity)

    suspend fun deleteFavorite(entity: MovieEntity) = dao.deleteMovie(entity)

    suspend fun existFavorite(id: Int): Boolean = dao.existsMovie(id)
}
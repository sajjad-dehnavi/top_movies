package dehnavi.sajjad.topmoview.repository

import dehnavi.sajjad.topmoview.database.dao.MoviesDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: MoviesDao) {

    fun getAllFavorite() = dao.getAllMovies()
}
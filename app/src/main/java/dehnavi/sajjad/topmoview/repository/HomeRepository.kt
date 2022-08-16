package dehnavi.sajjad.topmoview.repository

import dehnavi.sajjad.topmoview.api.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun topMoviesList(id: Int) = apiService.moviesTopList(id)

    suspend fun listMovies() = apiService.listMovies()

    suspend fun genreList() = apiService.genreList()
}
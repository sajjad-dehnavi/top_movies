package dehnavi.sajjad.topmoview.repository

import dehnavi.sajjad.topmoview.api.ApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun searchMovie(name: String) = apiService.search(name)
}
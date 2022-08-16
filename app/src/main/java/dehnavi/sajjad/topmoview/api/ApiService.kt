package dehnavi.sajjad.topmoview.api

import dehnavi.sajjad.topmoview.model.detail.ResponseDetailMovie
import dehnavi.sajjad.topmoview.model.home.ResponseGenreList
import dehnavi.sajjad.topmoview.model.home.ResponseMoviesList
import dehnavi.sajjad.topmoview.model.register.BodyRegister
import dehnavi.sajjad.topmoview.model.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("register")
    suspend fun registerUser(@Body bodyRegister: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesList>

    @GET("movies/{movie_id}")
    suspend fun movieDetail(@Path("movie_id") id: Int): Response<ResponseDetailMovie>

    @GET("movies")
    suspend fun listMovies(): Response<ResponseMoviesList>

    @GET("movies")
    suspend fun search(@Query("q") name: String): Response<ResponseMoviesList>

    @GET("genres")
    suspend fun genreList(): Response<ResponseGenreList>

}
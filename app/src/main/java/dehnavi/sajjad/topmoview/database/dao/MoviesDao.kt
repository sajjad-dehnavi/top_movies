package dehnavi.sajjad.topmoview.database.dao

import androidx.room.*
import dehnavi.sajjad.topmoview.database.entity.MovieEntity
import dehnavi.sajjad.topmoview.utils.Constant

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM ${Constant.TABLE_ENTITY}")
    fun getAllMovies(): MutableList<MovieEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constant.TABLE_ENTITY} WHERE id =:id)")
    suspend fun existsMovie(id: Int): Boolean
}
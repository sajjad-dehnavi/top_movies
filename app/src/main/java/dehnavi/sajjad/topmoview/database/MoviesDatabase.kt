package dehnavi.sajjad.topmoview.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dehnavi.sajjad.topmoview.database.dao.MoviesDao
import dehnavi.sajjad.topmoview.database.entity.MovieEntity
import dehnavi.sajjad.topmoview.utils.Constant

@Database(entities = [MovieEntity::class], version = Constant.DB_VERSION, exportSchema = false)
abstract class MoviesDatabase :RoomDatabase(){
    abstract fun movieDao( ):MoviesDao
}
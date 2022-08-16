package dehnavi.sajjad.topmoview.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dehnavi.sajjad.topmoview.database.MoviesDatabase
import dehnavi.sajjad.topmoview.database.entity.MovieEntity
import dehnavi.sajjad.topmoview.utils.Constant
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, MoviesDatabase::class.java, Constant.DB_NAME
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideMovieDao(db: MoviesDatabase) = db.movieDao()

    @Provides
    @Singleton
    fun provideMovieEntity() = MovieEntity()
}
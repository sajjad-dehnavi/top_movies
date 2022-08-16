package dehnavi.sajjad.topmoview.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dehnavi.sajjad.topmoview.utils.Constant

@Entity(tableName = Constant.TABLE_ENTITY)
data class MovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var country: String = "",
    var title: String = "",
    var genres: String = "",
    var imdbRating: String = "",
    var year: String = "",
    var poster: String = "",
)

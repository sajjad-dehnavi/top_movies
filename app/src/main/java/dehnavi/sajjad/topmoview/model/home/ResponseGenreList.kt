package dehnavi.sajjad.topmoview.model.home


import com.google.gson.annotations.SerializedName

class ResponseGenreList : ArrayList<ResponseGenreList.ResponseGenreListItem>() {

    data class ResponseGenreListItem(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?
    )
}
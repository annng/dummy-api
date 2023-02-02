package technicaltest.com.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import technicaltest.com.app.data.local.converter.PostConverter
import technicaltest.com.app.data.model.User

@Entity(tableName = "post")
@TypeConverters(PostConverter::class)
data class PostEntity(
    @PrimaryKey
    @SerializedName("id")
    var id: String,
    @SerializedName("text")
    var text: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("likes")
    var likes: Int = 0,
    @SerializedName("tags")
    var tags: List<String>?,
    @SerializedName("link")
    var link: String?,
    @SerializedName("publishDate")
    var publishDate: String?,
    @SerializedName("owner")
    var owner: User?,
) : java.io.Serializable {

}
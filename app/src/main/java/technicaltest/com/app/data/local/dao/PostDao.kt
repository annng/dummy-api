package technicaltest.com.app.data.local.dao

import androidx.room.*
import technicaltest.com.app.data.local.entity.PostEntity


@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getPosts(): List<PostEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg post: PostEntity)

    @Query("DELETE FROM post WHERE id IN (:id)")
    fun deletePost(id : String?)

    @Query("SELECT EXISTS (SELECT * FROM post WHERE id IN (:id))")
    fun isExist(id : String?) : Boolean
}
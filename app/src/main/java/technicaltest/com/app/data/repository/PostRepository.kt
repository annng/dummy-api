package technicaltest.com.app.data.repository

import retrofit2.Response
import technicaltest.com.app.data.local.dao.PostDao
import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.remote.domain.PostServices
import technicaltest.com.app.data.remote.domain.UserServices

class PostRepository(
    private val postServices: PostServices,
    private val postDao: PostDao,
) {
    suspend fun getPost(limit : Int, page : Int) = postServices.getPost(limit, page)
    suspend fun getPostByTag(tag : String, limit : Int, page : Int) = postServices.getPostByTag(tag, limit, page)
    suspend fun getUserPost(userId : String, limit : Int, page : Int) = postServices.getUserPost(userId, limit, page)

    fun getLikePosts() = postDao.getPosts()
    fun addLike(post : PostEntity) = postDao.insert(post)
    fun deleteLike(idPost : String) = postDao.deletePost(idPost)
    fun isLikeExist(post : PostEntity) = postDao.isExist(post.id)
}
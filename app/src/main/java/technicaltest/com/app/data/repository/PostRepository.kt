package technicaltest.com.app.data.repository

import android.util.Log
import technicaltest.com.app.core.common.util.validator.EncryptionHelper
import technicaltest.com.app.data.local.SharedPreference
import technicaltest.com.app.data.local.dao.PostDao
import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.model.Sample
import technicaltest.com.app.data.remote.domain.PostServices

class PostRepository(
    private val postServices: PostServices,
    private val postDao: PostDao,
    private val sharedPreference: SharedPreference
) {
    suspend fun getPost(limit: Int, page: Int) = postServices.getPost(limit, page)
    suspend fun getPostByTag(tag: String, limit: Int, page: Int) =
        postServices.getPostByTag(tag, limit, page)

    suspend fun getUserPost(userId: String, limit: Int, page: Int) =
        postServices.getUserPost(userId, limit, page)

    suspend fun getLikePosts() = postDao.getPosts()

    suspend fun addLike(post: PostEntity) {
        val encryptionHelper = EncryptionHelper()

        if (sharedPreference.getKey() == null) {
            sharedPreference.saveKey(encryptionHelper.generateKey())
        }

        val key = sharedPreference.getKey()

        val encryptionPost = key?.let {
            val encrypted = encryptionHelper.encryptObject(post, it)
            encryptionHelper.decryptObject(encrypted, it) as Sample
        }

        postDao.insert(encryptionPost as PostEntity)
    }

    suspend fun deleteLike(idPost: String) = postDao.deletePost(idPost)
    fun isLikeExist(idPost: String) = postDao.isExist(idPost)
}
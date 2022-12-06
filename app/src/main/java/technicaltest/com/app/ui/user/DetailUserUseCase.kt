package technicaltest.com.app.ui.user

import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.repository.PostRepository
import technicaltest.com.app.data.repository.UserRepository

class DetailUserUseCase(
    private val userRepo: UserRepository,
    private val postRepo: PostRepository
) {
    suspend fun getUserDetail(userId : String) = userRepo.getUserDetail(userId)
    suspend fun getPost(userId : String, limit : Int, page : Int) = postRepo.getUserPost(userId, limit, page)

    fun getLikePosts() = postRepo.getLikePosts()
    fun addLike(postEntity: PostEntity) = postRepo.addLike(postEntity)
    fun deleteLike(idPost: String) = postRepo.deleteLike(idPost)
}
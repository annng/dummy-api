package technicaltest.com.app.ui.main.child.like

import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.repository.PostRepository

class LikeUseCase(
    private val postRepository: PostRepository
) {
    suspend fun getLikePosts() = postRepository.getLikePosts()
    suspend fun addLike(postEntity: PostEntity) = postRepository.addLike(postEntity)
    suspend fun deleteLike(idPost: String) = postRepository.deleteLike(idPost)
    fun isLikeExist(idPost: String) = postRepository.isLikeExist(idPost)
}
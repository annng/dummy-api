package technicaltest.com.app.ui.main.child.like

import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.repository.PostRepository

class LikeUseCase(
    private val postRepository: PostRepository
) {
    fun getLikePosts() = postRepository.getLikePosts()
    fun addLike(postEntity: PostEntity) = postRepository.addLike(postEntity)
    fun deleteLike(idPost: String) = postRepository.deleteLike(idPost)
}
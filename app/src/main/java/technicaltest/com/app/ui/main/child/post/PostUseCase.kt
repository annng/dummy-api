package technicaltest.com.app.ui.main.child.post

import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.repository.PostRepository

class PostUseCase(private val postRepository: PostRepository) {
    suspend fun getPost(limit : Int, page : Int) = postRepository.getPost(limit, page)
    suspend fun getPostByTag(tag : String, limit : Int, page : Int) = postRepository.getPostByTag(tag, limit, page)

    suspend fun getLikePosts() = postRepository.getLikePosts()
    suspend fun addLike(postEntity: PostEntity) = postRepository.addLike(postEntity)
    suspend fun deleteLike(idPost: String) = postRepository.deleteLike(idPost)
}
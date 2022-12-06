package technicaltest.com.app.ui.main.child.like

import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.ui.base.BaseViewModel

class LikeViewModel(
    private val useCase: LikeUseCase
) : BaseViewModel() {
    fun getLikePosts() = useCase.getLikePosts()
    fun addLike(postEntity: PostEntity) = useCase.addLike(postEntity)
    fun deleteLike(idPost: String) = useCase.deleteLike(idPost)
}
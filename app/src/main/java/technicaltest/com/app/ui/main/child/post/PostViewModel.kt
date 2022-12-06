package technicaltest.com.app.ui.main.child.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import technicaltest.com.app.core.common.cons.RequestCons
import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.model.*
import technicaltest.com.app.ui.base.BaseViewModel

class PostViewModel(
    private val useCase: PostUseCase
) : BaseViewModel() {
    private val _post = MutableLiveData<ResultState<BaseResponseData<Post>>>()
    val post: LiveData<ResultState<BaseResponseData<Post>>> = _post

    fun getPost(limit: Int = RequestCons.QUERY.LIMIT, page: Int, tag : String?) {
        loaderState.value = LoaderState.OnLoading(true)
        viewModelScope.launch {
            try {
                val response = if(tag == null) useCase.getPost(limit, page) else useCase.getPostByTag(tag, limit, page)
                if (response.isSuccessful) {
                    _post.postValue(ResultState.Success(response.body()))
                } else {
                    _post.postValue(
                        ResultState.Error(
                            errorCode = response.code(),
                            data = response.errorBody()
                        )
                    )
                }
                loaderState.value = LoaderState.OnLoading(false)
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                _post.postValue(ResultState.Error(exception = throwable))
                loaderState.value = LoaderState.OnLoading(false)
            }
        }
    }

    fun getLikePosts() = useCase.getLikePosts()
    fun addLike(postEntity: PostEntity) = useCase.addLike(postEntity)
    fun deleteLike(idPost: String) = useCase.deleteLike(idPost)
}
package technicaltest.com.app.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import technicaltest.com.app.core.common.cons.RequestCons
import technicaltest.com.app.data.local.dao.PostDao
import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.model.*
import technicaltest.com.app.ui.base.BaseViewModel

class DetailUserViewModel(
    private val useCase : DetailUserUseCase,
    private val postDao: PostDao
) : BaseViewModel() {

    private val _user = MutableLiveData<ResultState<User>>()
    val user: LiveData<ResultState<User>> = _user

    fun getUser(userId : String) {
        loaderState.value = LoaderState.OnLoading(true)
        viewModelScope.launch {
            try {
                val response = useCase.getUserDetail(userId)
                if (response.isSuccessful) {
                    _user.postValue(ResultState.Success(response.body()))
                } else {
                    _user.postValue(
                        ResultState.Error(
                            errorCode = response.code(),
                            data = response.errorBody()
                        )
                    )
                }
                loaderState.value = LoaderState.OnLoading(false)
            } catch (throwable: Throwable) {
                _user.postValue(ResultState.Error(exception = throwable))
                loaderState.value = LoaderState.OnLoading(false)
            }
        }
    }

    private val _post = MutableLiveData<ResultState<BaseResponseData<Post>>>()
    val post: LiveData<ResultState<BaseResponseData<Post>>> = _post

    fun getPost(userId : String, limit: Int = RequestCons.QUERY.LIMIT, page: Int) {
        loaderState.value = LoaderState.OnLoading(true)
        viewModelScope.launch {
            try {
                val response = useCase.getPost(userId, limit, page)
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
                _post.postValue(ResultState.Error(exception = throwable))
                loaderState.value = LoaderState.OnLoading(false)
            }
        }
    }


    fun getPostDao() = postDao
    fun getLikePosts() = useCase.getLikePosts()
    fun addLike(postEntity: PostEntity) = useCase.addLike(postEntity)
    fun deleteLike(idPost: String) = useCase.deleteLike(idPost)

}
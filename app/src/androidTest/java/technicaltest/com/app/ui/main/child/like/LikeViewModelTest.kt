package technicaltest.com.app.ui.main.child.like

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.test.KoinTest
import org.koin.test.inject
import technicaltest.com.app.data.local.sample.SamplePost
import technicaltest.com.app.data.repository.PostRepository
import technicaltest.com.app.util.getOrAwaitValue

@RunWith(AndroidJUnit4ClassRunner::class)
class LikeViewModelTest : KoinTest {

    private val likeViewModel by inject<LikeViewModel>()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addPostAndRead(){
        likeViewModel.addLike(SamplePost.post)
        likeViewModel.getLikePosts()
        val result = likeViewModel.post.getOrAwaitValue().find {
            it.text == "Labrador"
        }

        assertThat(result != null).isEqualTo(true)
    }

    @Test
    fun addPostAndReadWrong(){
        likeViewModel.addLike(SamplePost.post)
        likeViewModel.getLikePosts()
        val result = likeViewModel.post.getOrAwaitValue().find {
            it.text == "ABC"
        }

        assertThat(result == null).isEqualTo(true)
    }

    @Test
    fun deleteAndRead(){
        likeViewModel.deleteLike(SamplePost.post.id)
        likeViewModel.getLikePosts()
        val result = likeViewModel.post.getOrAwaitValue().find {
            it.id == "1"
        }

        assertThat(result == null).isEqualTo(true)
    }
}
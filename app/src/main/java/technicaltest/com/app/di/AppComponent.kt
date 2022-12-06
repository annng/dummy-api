package technicaltest.com.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import technicaltest.com.app.core.shared.api.RetrofitServices
import technicaltest.com.app.data.local.CustomRoomDatabase
import technicaltest.com.app.data.remote.domain.PostServices
import technicaltest.com.app.data.remote.domain.UserServices
import technicaltest.com.app.data.repository.PostRepository
import technicaltest.com.app.data.repository.UserRepository
import technicaltest.com.app.ui.base.BaseUseCase
import technicaltest.com.app.ui.base.BaseViewModel
import technicaltest.com.app.ui.main.child.like.LikeUseCase
import technicaltest.com.app.ui.main.child.like.LikeViewModel
import technicaltest.com.app.ui.main.child.post.PostUseCase
import technicaltest.com.app.ui.main.child.post.PostViewModel
import technicaltest.com.app.ui.main.child.user.UserUseCase
import technicaltest.com.app.ui.main.child.user.UserViewModel
import technicaltest.com.app.ui.user.DetailUserUseCase
import technicaltest.com.app.ui.user.DetailUserViewModel


val localModule = module {
    single { CustomRoomDatabase.getDatabase(get()).postDao() }
}

val networkModule = module {
    single { RetrofitServices.endpointAPI<UserServices>(get()) }
    single { RetrofitServices.endpointAPI<PostServices>(get()) }
}

val dataSourceModule = module {
    single { UserRepository(get()) }
    single { PostRepository(get(), get()) }
}

val useCaseModule = module {
    single { BaseUseCase() }
    single { UserUseCase(get()) }
    single { PostUseCase(get()) }
    single { DetailUserUseCase(get(), get()) }
    single { LikeUseCase(get()) }
}

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { UserViewModel(get()) }
    viewModel { PostViewModel(get()) }
    viewModel { DetailUserViewModel(get(), get()) }
    viewModel { LikeViewModel(get()) }
}


val appComponent: List<Module> = listOf(
    dataSourceModule,
    networkModule,
    viewModelModule,
    useCaseModule,
    localModule,
)
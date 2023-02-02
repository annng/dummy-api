package technicaltest.com.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import technicaltest.com.app.core.shared.api.RetrofitServices
import technicaltest.com.app.data.local.CustomRoomDatabase
import technicaltest.com.app.data.local.SharedPreference
import technicaltest.com.app.data.remote.domain.PostServices
import technicaltest.com.app.data.remote.domain.UserServices
import technicaltest.com.app.data.repository.PostRepository
import technicaltest.com.app.data.repository.UserRepository
import technicaltest.com.app.ui.base.BaseUseCase
import technicaltest.com.app.ui.base.BaseViewModel
import technicaltest.com.app.ui.main.child.like.LikeUseCase
import technicaltest.com.app.ui.main.child.like.LikeViewModel


val localModule = module {
    single { CustomRoomDatabase.getDatabase(get()).postDao() }
}

val networkModule = module {
    single { RetrofitServices.endpointAPI<UserServices>(get()) }
    single { RetrofitServices.endpointAPI<PostServices>(get()) }
}

val dataSourceModule = module {
    single { UserRepository(get()) }
    single { PostRepository(get(), get(), get()) }
}

val useCaseModule = module {
    single { BaseUseCase() }
    single { LikeUseCase(get()) }
}

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { LikeViewModel(get()) }
}

val appModule = module {
    single { SharedPreference(get()) }
}

val appComponent: List<Module> = listOf(
    appModule,
    dataSourceModule,
    networkModule,
    viewModelModule,
    useCaseModule,
    localModule,
)
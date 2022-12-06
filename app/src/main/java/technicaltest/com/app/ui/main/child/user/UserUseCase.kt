package technicaltest.com.app.ui.main.child.user

import technicaltest.com.app.data.repository.UserRepository

class UserUseCase(private val userRepo : UserRepository)  {

    suspend fun getUser(limit : Int, page : Int) = userRepo.getUser(limit, page)

}